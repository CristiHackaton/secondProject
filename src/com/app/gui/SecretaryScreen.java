package com.app.gui;

import java.awt.Rectangle;

import javax.crypto.spec.OAEPParameterSpec;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.app.db.model.Consultation;
import com.app.db.model.Doctor;
import com.app.db.model.Pacient;
import com.app.db.model.User;
import com.app.service.SecretaryService;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import java.awt.ScrollPane;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SecretaryScreen extends JFrame{
	private User loggedUser;
	private SecretaryService secretServ;
	private Consultation cons;
	private JTextField namePatient;
	private JTextField cnp;
	private JTextField idCard;
	private JTextField birth;
	private JTextField adress;
	private JTextField patient;
	private JTextField date;
	private JTextField duration;
	private JTextField doctor;
	private JList listConsult;
	private JList listPatient;
	private JList listDoc;
	private JTextArea notes;
	private JScrollPane scrollPaneCons;
	private JScrollPane scrollPaneDoc;
	private JScrollPane scrollPanePatient;
	private JPanel panelConsultations;
	private JPanel panelPatient;
	private JButton btnSchedule;
	private int id;
	private boolean isNew=false;
	private int idCons;
	private Pacient selectedPacient;
	public SecretaryScreen(User user) {
		
		secretServ=new SecretaryService();
	
		setTitle("Secretary");
		getContentPane().setBounds(new Rectangle(10, 10, 770, 1777));
		getContentPane().setLayout(null);
		this.setVisible(true);
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 434, 21);
		getContentPane().add(menuBar);
		
		///////////**********Menu Patients******************/////////////////////
		JMenu mnPatients = new JMenu("Patients");
		menuBar.add(mnPatients);
		
		JMenuItem mntmAdd = new JMenuItem("Add ");
		mnPatients.add(mntmAdd);
		
		JMenuItem mntmViewAllPat= new JMenuItem("View All");
		mnPatients.add(mntmViewAllPat);
		
		JMenuItem mntmUpdatePat = new JMenuItem("Update");
		mnPatients.add(mntmUpdatePat);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnPatients.add(mntmSave);
		
		mntmAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				isNew=true;
				namePatient.setText("");
				cnp.setText("");
				idCard.setText("");
				birth.setText("");
				adress.setText("");
				
			}
		});
		mntmViewAllPat.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panelPatient.setVisible(true);
				panelConsultations.setVisible(false);
				panelConsultations.repaint();
				panelConsultations.revalidate();
				panelPatient.repaint();
				panelPatient.revalidate();
			}
		});
		
		
		mntmUpdatePat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Pacient p=(Pacient) listPatient.getSelectedValue();
				namePatient.setText(p.getName());
				cnp.setText(p.getCnp());
				idCard.setText(p.getIdentitiCard());
				adress.setText(p.getAddress());
				SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
				String date = DATE_FORMAT.format(p.getBirth());
		        birth.setText(date);
				
				isNew=false;
				id=p.getId();
				
			}
		});
		mntmSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Pacient p=new Pacient();
				SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
				Date date=null;
				try {
					date = DATE_FORMAT.parse(birth.getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				p.setAddress(adress.getText());
				p.setBirth(date);
				p.setCnp(cnp.getText());
				p.setIdentitiCard(idCard.getText());
				p.setName(namePatient.getText());
				
				if(isNew){
					secretServ.addPaticient(loggedUser, p);
				}else {p.setId(id);
					secretServ.updatePacient(loggedUser, p);
				}
				cleanAndRedraw();
			}
			
		});
		///////////************Menu Consultations******************///////////////////
			
		
		JMenu mnConsultations = new JMenu("Consultations");
		menuBar.add(mnConsultations);
		
		JMenuItem mntmViewAllCons = new JMenuItem("View All ");
		mnConsultations.add(mntmViewAllCons);
		
		JMenuItem mntmCreate = new JMenuItem("Create");
		mnConsultations.add(mntmCreate);
		
		JMenuItem mntmUpdateCons = new JMenuItem("Update");
		mnConsultations.add(mntmUpdateCons);
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mnConsultations.add(mntmDelete);
		
		JMenuItem mntmSave_1 = new JMenuItem("Save");
		mnConsultations.add(mntmSave_1);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenuItem mntmLogOut = new JMenuItem("Log out");
		mnOptions.add(mntmLogOut);
		/////////////*******************************/////////////////
		mntmViewAllCons.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				panelPatient.setVisible(false);
				panelConsultations.setVisible(true);
				panelConsultations.repaint();
				panelConsultations.revalidate();
				panelPatient.repaint();
				panelPatient.revalidate();
				listConsult.setVisible(true);
				getContentPane().repaint();
				getContentPane().revalidate();
				
			}
		});
		mntmCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				panelConsultations.setVisible(false);
				panelPatient.setVisible(true);
				panelConsultations.repaint();
				panelConsultations.revalidate();
				panelPatient.repaint();
				panelPatient.revalidate();
				isNew=true;
				patient.setText("");
				date.setText("");
				duration.setText("");
				doctor.setText("");
				notes.setText("");
		
			}
		});
		mntmDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cons=(Consultation) listConsult.getSelectedValue();
				secretServ.deleteConsultation(loggedUser, cons);
			}
		});
		mntmUpdateCons.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Consultation consult = (Consultation) listConsult.getSelectedValue();
				isNew=false;
				idCons= consult.getId();
				patient.setText(consult.getPacient().getName());
				
				SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
				String dateTo = DATE_FORMAT.format(consult.getConsultationDate());
				
				date.setText(dateTo);
				duration.setText(consult.getDuration() + "");
				doctor.setText(consult.getDoctor().getUsername());
				notes.setText(consult.getNotes());
				
			}
		});
		mntmSave_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Consultation c=new Consultation();
				SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
				Date datecomp=null;
				try {
					datecomp = DATE_FORMAT.parse(date.getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				c.setPacient(selectedPacient); 
				c.setDuration(Float.parseFloat(duration.getText()));
				c.setConsultationDate(datecomp);
				c.setDoctor((Doctor) listDoc.getSelectedValue());
				c.setNotes(notes.getText());
				if (isNew){
					secretServ.createConsultation(loggedUser, c);
				}else {
					c.setId(idCons);
					secretServ.updateConsultation(loggedUser, c);
				}
				cleanAndRedraw();
				date.setText("");
				duration.setText("");
				patient.setText("");
				notes.setText("");
				doctor.setText("");
			}

				
			
		});
		mntmLogOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				getContentPane().setVisible(false);
				LoginScreen log=new LoginScreen();
				log.setVisible(true);
				cleanAndRedraw();
			}
		});
	
		
		panelConsultations = new JPanel();
		panelConsultations.setBounds(15, 15, 1200, 600);
		getContentPane().add(panelConsultations);
		panelConsultations.setLayout(null);
		
		JLabel lblName = new JLabel("Patient");
		lblName.setBounds(10, 22, 46, 14);
		panelConsultations.add(lblName);
		
		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setBounds(10, 73, 46, 14);
		panelConsultations.add(lblDuration);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 48, 46, 14);
		panelConsultations.add(lblDate);
		
		patient = new JTextField();
		patient.setBounds(84, 19, 86, 20);
		panelConsultations.add(patient);
		patient.setColumns(10);
		
		date = new JTextField();
		date.setBounds(84, 45, 86, 20);
		panelConsultations.add(date);
		date.setColumns(10);
		
		duration = new JTextField();
		duration.setBounds(84, 70, 86, 20);
		panelConsultations.add(duration);
		duration.setColumns(10);
		
		JLabel lblDoctor = new JLabel("Doctor");
		lblDoctor.setBounds(10, 120, 46, 14);
		panelConsultations.add(lblDoctor);
		
		doctor = new JTextField();
		doctor.setBounds(84, 117, 86, 20);
		panelConsultations.add(doctor);
		doctor.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Notes");
		lblNewLabel.setBounds(10, 158, 46, 14);
		panelConsultations.add(lblNewLabel);
		
		notes = new JTextArea();
		notes.setBounds(84, 148, 109, 64);
		panelConsultations.add(notes);
		
		listConsult = new JList(new ConsultationListModel(secretServ.getAllConsultations(loggedUser)));

		listConsult.setBounds(341, 22, 96, 164);
		
		scrollPaneCons = new JScrollPane(listConsult);
		scrollPaneCons.setBounds(341, 22, 96, 164);
		panelConsultations.add(scrollPaneCons);
		
		listDoc= new JList(new DoctorListModel(secretServ.getAllDoctors(loggedUser)));

		listDoc.setBounds(10, 10, 96, 164);
		
		scrollPaneDoc = new JScrollPane(listDoc);
		scrollPaneDoc.setBounds(441, 22, 96, 164);
		panelConsultations.add(scrollPaneDoc);
		scrollPaneDoc.setVisible(true);
		
		
		
		/////////////**************************************************/////////////////////
		
		
		panelPatient = new JPanel();
		panelPatient.setBounds(25, 25,600, 600);
		getContentPane().add(panelPatient);
		panelPatient.setLayout(null);
		
		JLabel lblCnp = new JLabel("CNP");
		lblCnp.setBounds(10, 86, 46, 14);
		panelPatient.add(lblCnp);
		
		JLabel lblNume = new JLabel("Name");
		lblNume.setBounds(10, 59, 46, 14);
		panelPatient.add(lblNume);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setBounds(10, 111, 61, 14);
		panelPatient.add(lblDateOfBirth);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 157, 46, 14);
		panelPatient.add(lblAddress);
		
		JLabel lblIdentityCard = new JLabel("Identity Card");
		lblIdentityCard.setBounds(10, 132, 76, 14);
		panelPatient.add(lblIdentityCard);
		
		cnp = new JTextField();
		cnp.setBounds(95, 81, 86, 20);
		panelPatient.add(cnp);
		cnp.setColumns(10);
		
		idCard = new JTextField();
		idCard.setBounds(95, 128, 86, 20);
		panelPatient.add(idCard);
		idCard.setColumns(10);
		
		birth = new JTextField();
		birth.setBounds(94, 105, 86, 20);
		panelPatient.add(birth);
		birth.setColumns(10);
		
		adress = new JTextField();
		adress.setBounds(96, 154, 86, 20);
		panelPatient.add(adress);
		adress.setColumns(10);
		
		namePatient = new JTextField();
		namePatient.setBounds(93, 54, 86, 20);
		panelPatient.add(namePatient);
		namePatient.setColumns(10);
//		
//		cnp.setEditable(false);
//		idCard.setEditable(false);
//		birth.setEditable(false);
		
		listPatient = new JList(new PatientListModel(secretServ.getAllPatients(loggedUser)));

		listPatient.setBounds(346, 171, 89, 158);
		
		scrollPanePatient = new JScrollPane(listPatient);
		scrollPanePatient.setBounds(346, 171, 89, 158);
		panelPatient.add(scrollPanePatient);
		
		btnSchedule = new JButton("Schedule");
		btnSchedule.setBounds(224, 18, 89, 23);
		panelPatient.add(btnSchedule);
		loggedUser = user;
btnSchedule.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selectedPacient=(Pacient) listPatient.getSelectedValue();
				listPatient.setSelectedValue(selectedPacient, true);
				panelPatient.setVisible(false);
				panelConsultations.setVisible(true);
				panelPatient.repaint();
				panelConsultations.repaint();
				panelConsultations.revalidate();
				panelPatient.revalidate();
			}
		});
		
		panelPatient.setVisible(false);
		panelConsultations.setVisible(false);
		panelConsultations.repaint();
		panelConsultations.revalidate();
		panelPatient.repaint();
		panelPatient.revalidate();
		listConsult.setVisible(true);
		listDoc.setVisible(true);
	}
	private void cleanAndRedraw() {
		panelConsultations.remove(scrollPaneCons);
		panelPatient.remove(scrollPanePatient);
		panelConsultations.remove(scrollPaneDoc);
		listConsult = new JList(new ConsultationListModel(secretServ.getAllConsultations(loggedUser)));

		listConsult.setBounds(341, 22, 96, 164);
		
		scrollPaneCons = new JScrollPane(listConsult);
		scrollPaneCons.setBounds(341, 22, 96, 164);
		panelConsultations.add(scrollPaneCons);
		
		listDoc= new JList(new DoctorListModel(secretServ.getAllDoctors(loggedUser)));

		listDoc.setBounds(10, 10, 96, 164);
		
		scrollPaneDoc = new JScrollPane(listDoc);
		scrollPaneDoc.setBounds(441, 22, 96, 164);
		panelConsultations.add(scrollPaneDoc);
		scrollPaneDoc.setVisible(true);

		listPatient = new JList(new PatientListModel(secretServ.getAllPatients(loggedUser)));

		listPatient.setBounds(346, 171, 89, 158);
		
		scrollPanePatient = new JScrollPane(listPatient);
		scrollPanePatient.setBounds(346, 171, 89, 158);
		panelPatient.add(scrollPanePatient);
		
		getContentPane().setVisible(false);
		getContentPane().repaint();
		getContentPane().revalidate();
		panelConsultations.repaint();
		panelConsultations.revalidate();
		panelPatient.repaint();
		panelPatient.revalidate();
		
		
	
		//initComponents(loggedUser);
		//refreshPage();		
		getContentPane().setVisible(true);

}


	public void setLoggedUser(User user) {
		this.loggedUser = user;
		secretServ=new SecretaryService();
	}
	public static void main(String args[]) {
		User u = new User();
		u.setUserID(3);
		u.setUsername("aaaa");
		SecretaryScreen scr = new SecretaryScreen(u);
		scr.setVisible(true);
	}
}
	


package com.app.gui;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.app.db.model.Consultation;
import com.app.db.model.User;
import com.app.service.SecretaryService;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import java.awt.List;

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

	public SecretaryScreen(User user) {
		setTitle("Secretary");
		getContentPane().setBounds(new Rectangle(10, 10, 770, 1777));
		getContentPane().setLayout(null);
		
		JPanel panelConsultations = new JPanel();
		panelConsultations.setBounds(10, 10, 365, 301);
		getContentPane().add(panelConsultations);
		panelConsultations.setLayout(null);
		
		JLabel lblNa = new JLabel("Patient");
		lblNa.setBounds(10, 22, 46, 14);
		panelConsultations.add(lblNa);
		
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
		
		JTextArea notes = new JTextArea();
		notes.setBounds(84, 148, 109, 64);
		panelConsultations.add(notes);
		
		listConsult = new JList(new ConsultaionModel(secretServ.getAllConsultations()));

		listConsult.setBounds(341, 22, 96, 164);
		
		JScrollPane scrollPaneCons = new JScrollPane(listConsult);
		scrollPaneCons.setBounds(341, 22, 96, 164);
		panelConsultations.add(scrollPaneCons);
		
		
		JPanel panelPatient = new JPanel();
		panelPatient.setBounds(0, 0, 365, 251);
		getContentPane().add(panelPatient);
		panelPatient.setLayout(null);
		
		JLabel lblCnp = new JLabel("CNP");
		lblCnp.setBounds(10, 86, 46, 14);
		panelPatient.add(lblCnp);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 59, 46, 14);
		panelPatient.add(lblName);
		
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
		
		listPatient = new JList(new PatientListModel(secretServ.()));

		listPatient.setBounds(346, 171, 89, 158);
		
		JScrollPane scrollPanePatient = new JScrollPane();
		scrollPanePatient.setBounds(346, 171, 89, 158);
		panelPatient.add(scrollPanePatient);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
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
		loggedUser = user;
	}

	public void setLoggedUser(User user) {
		this.loggedUser = user;
		secretServ=new SecretaryService();
	}
}
	


package com.app.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;

import com.app.db.model.Consultation;
import com.app.db.model.Pacient;
import com.app.db.model.User;
import com.app.service.DoctorService;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class DoctorScreen extends JFrame {
	private JScrollPane listPanel;
	private JPanel panelVizualizare;
	private JList pacientList;
	private JList consultationsList;
	private User loggedUser;
	private JTextField durationTextField;
	private JTextField dateTextField;
	private DoctorService docService;
	private JScrollPane consultScrollPane;
	private JScrollPane pacientiScrollPane;
	private JTextPane notesPane;

	public DoctorScreen(User user) {
		loggedUser = user;
		docService = new DoctorService();
		setTitle("Welcome Doctor!");
		getContentPane().setLayout(null);
		
		panelVizualizare = new JPanel();
		panelVizualizare.setBounds(0, 0, 710, 409);
		getContentPane().add(panelVizualizare);
		panelVizualizare.setLayout(null);
		
		JButton btnEditConsultation = new JButton("Edit consultation");
		btnEditConsultation.setBounds(409, 45, 154, 23);
		panelVizualizare.add(btnEditConsultation);
		
		btnEditConsultation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Consultation c = (Consultation) consultationsList.getSelectedValue();
				durationTextField.setText("" + c.getDuration());
				
			}
		});
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(409, 79, 154, 23);
		panelVizualizare.add(btnSave);
		
		durationTextField = new JTextField();
		durationTextField.setBounds(124, 247, 154, 20);
		panelVizualizare.add(durationTextField);
		durationTextField.setColumns(10);
		
		dateTextField = new JTextField();
		dateTextField.setBounds(124, 279, 154, 20);
		panelVizualizare.add(dateTextField);
		dateTextField.setColumns(10);
		
		notesPane = new JTextPane();
		notesPane.setBounds(122, 310, 248, 88);
		panelVizualizare.add(notesPane);
		
		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setBounds(30, 250, 61, 14);
		panelVizualizare.add(lblDuration);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(30, 282, 46, 14);
		panelVizualizare.add(lblDate);
		
		JLabel lblNotes = new JLabel("Notes");
		lblNotes.setBounds(30, 310, 46, 14);
		panelVizualizare.add(lblNotes);
		
		pacientList = new JList(new PatientListModel(docService.getAllPatientsForDoctor(loggedUser)));
		
		pacientiScrollPane = new JScrollPane(pacientList);
		pacientiScrollPane.setBounds(10, 11, 154, 225);
		panelVizualizare.add(pacientiScrollPane);
		
		consultScrollPane = new JScrollPane();
		consultScrollPane.setBounds(216, 11, 154, 225);
		panelVizualizare.add(consultScrollPane);	
		
		
		pacientList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				panelVizualizare.remove(consultScrollPane);
				
				Pacient p = (Pacient) pacientList.getSelectedValue();
				consultationsList = new JList(new ConsultationListModel(docService.getAllConsultationsForPacient(loggedUser, p)));
				
				consultScrollPane = new JScrollPane(consultationsList);
				consultScrollPane.setBounds(216, 11, 154, 225);
				panelVizualizare.add(consultScrollPane);	
				consultScrollPane.setVisible(true);
				consultScrollPane.repaint();
				consultScrollPane.revalidate();
				panelVizualizare.repaint();
				panelVizualizare.revalidate();
			}
		});
		
//		consultScrollPane = new JScrollPane();
//		consultScrollPane.setBounds(216, 11, 154, 225);
//		panelVizualizare.add(consultScrollPane);		
		
	}

	public void setLoggedUser(User user) {
		this.loggedUser = user;

	}

	public static void main(String args[]) {
		User u = new User();
		u.setUserID(2);
		DoctorScreen scr = new DoctorScreen(u);
		scr.setVisible(true);
	}
}

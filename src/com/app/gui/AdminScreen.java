package com.app.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.xml.ws.handler.MessageContext.Scope;

import com.app.db.model.User;
import com.app.service.AdminService;

public class AdminScreen extends JFrame{
	private JTextField userName;
	private JTextField password;
	private JTextField email;
	private AdminService adminServ;
	private boolean isNew=false;
	private int id;
	private User loggedUser;
	private JList userList;
	private JPanel backgroundPanel;
	
	//private UserService userServ;
	public AdminScreen(User user) {
		initComponents(user);
		refreshPage();
	}
	
	private void initComponents(User user) {
		loggedUser = user;
		adminServ = new AdminService();
		setBounds(new Rectangle(0, 0, 777, 1777));
		getContentPane().setLayout(null);
		
		backgroundPanel = new JPanel();
		backgroundPanel.setBounds(23, 11, 450, 238);
		getContentPane().add(backgroundPanel);
		backgroundPanel.setLayout(null);
		
		
		userList = new JList(new UserListModel(adminServ.getAllUsers(loggedUser)));
		
		userList.setBounds(10, 10, 85, 151);
		JScrollPane scrollPane = new JScrollPane(userList);
		scrollPane.setBounds(10, 10, 96, 161);
		
		backgroundPanel.add(scrollPane);
		userList.setVisible(true);
		userList.revalidate();
		scrollPane.revalidate();
		scrollPane.setVisible(true);
		
		JButton btnCreateUser = new JButton("Create User");
		btnCreateUser.setBounds(144, 22, 118, 23);
		backgroundPanel.add(btnCreateUser);
		
		userName = new JTextField();
		userName.setBounds(205, 57, 86, 20);
		backgroundPanel.add(userName);
		userName.setColumns(10);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(130, 60, 65, 14);
		backgroundPanel.add(lblUserName);
		
		password = new JTextField();
		password.setBounds(205, 89, 86, 20);
		backgroundPanel.add(password);
		password.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(130, 92, 46, 14);
		backgroundPanel.add(lblPassword);
		
		email = new JTextField();
		email.setBounds(205, 120, 86, 20);
		backgroundPanel.add(email);
		email.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(130, 123, 46, 14);
		backgroundPanel.add(lblEmail);
		
		final JRadioButton radioAdmin = new JRadioButton("Admin");
		radioAdmin.setBounds(125, 160, 109, 23);
		backgroundPanel.add(radioAdmin);
		
		final JRadioButton radioDoctor = new JRadioButton("Doctor");
		radioDoctor.setBounds(243, 160, 109, 23);
		backgroundPanel.add(radioDoctor);
		
		final JRadioButton radioSecretary = new JRadioButton("Secretary");
		radioSecretary.setBounds(353, 160, 109, 23);
		backgroundPanel.add(radioSecretary);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(335, 56, 89, 23);
		backgroundPanel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(335, 99, 89, 23);
		backgroundPanel.add(btnDelete);
		
		final JButton btnSave = new JButton("Save");
		btnSave.setBounds(361, 215, 89, 23);
		backgroundPanel.add(btnSave);
		
		
		btnCreateUser.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				userName.setText("");
				password.setText("");
				email.setText("");
				radioAdmin.setSelected(false);
				radioDoctor.setSelected(false);
				radioSecretary.setSelected(false);
				isNew=true;
				
				
				
			}
			
		});
		btnEdit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				User u=(User) userList.getSelectedValue();
				userName.setText(u.getUsername());
				password.setText(u.getPassword());
				email.setText(u.getEmail());
				if(u.isAdmin()){
					radioAdmin.setSelected(true);
				}else if(u.isDoctor()){
					radioDoctor.setSelected(true);
				}else if(u.isSecretary()){
					radioSecretary.setSelected(true);
				}
				isNew=false;
				id=u.getUserID();
				btnSave.setVisible(true);
			}
			
		});
		
		btnSave.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				User u=new User();
				u.setUsername(userName.getText());
				u.setPassword(password.getText());
				u.setEmail(email.getText());
				int type;
				if(radioAdmin.isSelected()){
					u.setUserType(0);
					
				}else if(radioDoctor.isSelected()){
					u.setUserType(1);
				}else if(radioSecretary.isSelected()){
					u.setUserType(2);
				}
				if(isNew){
					adminServ.createUser(loggedUser,u);
				}else {
					u.setUserID(id);
					adminServ.updateUser(loggedUser,u);
				}
				btnSave.setVisible(false);
				cleanAndRedraw();
			}

			
		});
		btnDelete.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				User u=(User)userList.getSelectedValue();
				adminServ.deleteUser(loggedUser,u);
				
				cleanAndRedraw();
			}
			
		});
	}
	private void refreshPage() {
		backgroundPanel.repaint();
		backgroundPanel.revalidate();
		
	}
	
	private void cleanAndRedraw() {
		backgroundPanel.setVisible(false);
		backgroundPanel.removeAll();
		
		initComponents(loggedUser);
		refreshPage();
		backgroundPanel.setVisible(true);
	}
	
	public void setLoggedUser(User user) {
		this.loggedUser = user;
		
	}

}

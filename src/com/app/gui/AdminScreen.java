package com.app.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

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
	//private UserService userServ;
	public AdminScreen(User user) {
		loggedUser = user;
		adminServ = new AdminService();
		setBounds(new Rectangle(0, 0, 777, 1777));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(23, 11, 450, 238);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 96, 161);
		panel.add(scrollPane);
		
		final JList list = new JList(new UserListModel(adminServ.getAllUsers(loggedUser)));
		scrollPane.add(list);
		list.setBounds(10, 10, 85, 151);
		
		JButton btnCreateUser = new JButton("Create User");
		btnCreateUser.setBounds(144, 22, 118, 23);
		panel.add(btnCreateUser);
		
		userName = new JTextField();
		userName.setBounds(205, 57, 86, 20);
		panel.add(userName);
		userName.setColumns(10);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(130, 60, 65, 14);
		panel.add(lblUserName);
		
		password = new JTextField();
		password.setBounds(205, 89, 86, 20);
		panel.add(password);
		password.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(130, 92, 46, 14);
		panel.add(lblPassword);
		
		email = new JTextField();
		email.setBounds(205, 120, 86, 20);
		panel.add(email);
		email.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(130, 123, 46, 14);
		panel.add(lblEmail);
		
		final JRadioButton radioAdmin = new JRadioButton("Admin");
		radioAdmin.setBounds(125, 160, 109, 23);
		panel.add(radioAdmin);
		
		final JRadioButton radioDoctor = new JRadioButton("Doctor");
		radioDoctor.setBounds(243, 160, 109, 23);
		panel.add(radioDoctor);
		
		final JRadioButton radioSecretary = new JRadioButton("Secretary");
		radioSecretary.setBounds(353, 160, 109, 23);
		panel.add(radioSecretary);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(335, 56, 89, 23);
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(335, 99, 89, 23);
		panel.add(btnDelete);
		
		final JButton btnSave = new JButton("Save");
		btnSave.setBounds(361, 215, 89, 23);
		panel.add(btnSave);
		
		
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
				User u=(User) list.getSelectedValue();
				userName.setText(u.getUsername());
				password.setText(u.getPassword());
				email.setText(u.getEmail());
				if(u.isAdmin()){
					radioAdmin.isSelected();
				}else if(u.isDoctor()){
					radioDoctor.isSelected();
				}else if(u.isSecretary()){
					radioSecretary.isSelected();
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
				((UserListModel)list.getModel()).getListaUseri().add(u);
				btnSave.setVisible(false);
			}
			
		});
		btnDelete.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				User u=(User)list.getSelectedValue();
				adminServ.deleteUser(loggedUser,u);
				
				
			}
			
		});

	}
	public void setLoggedUser(User user) {
		this.loggedUser = user;
		
	}

}

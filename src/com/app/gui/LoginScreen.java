package com.app.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.app.db.model.User;
import com.app.service.GeneralService;

public class LoginScreen extends JFrame {
	private JTextField userName;
	private JTextField password;
	private GeneralService genService;
	public LoginScreen() {
		genService=new GeneralService();
		setTitle("Login");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 424, 251);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(10, 11, 76, 14);
		panel.add(lblUserName);
		
		userName = new JTextField();
		userName.setBounds(86, 8, 86, 20);
		panel.add(userName);
		userName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 54, 46, 14);
		panel.add(lblPassword);
		
		password = new JTextField();
		password.setBounds(86, 51, 86, 20);
		panel.add(password);
		password.setColumns(10);
		
		JButton butonLogin = new JButton("Login");
		butonLogin.setBounds(10, 108, 89, 23);
		panel.add(butonLogin);
		butonLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String username=userName.getText();
				String pass=password.getText();
				User user =genService.login(username, pass);
				if(user!=null){
					closeScreen();
					if(user.isDoctor()){
						DoctorScreen docScreen=new DoctorScreen();
						docScreen.setLoggedUser(user);
						docScreen.setSize(1763,763);
						docScreen.setVisible(true);
					}else if(user.isAdmin()){
						AdminScreen adminScreen= new AdminScreen();
						adminScreen.setLoggedUser(user);
						adminScreen.setSize(1763,763);
						adminScreen.setVisible(true);
					}else if(user.isSecretary()){
						SecretaryScreen secretaryScreen=new SecretaryScreen();
						secretaryScreen.setLoggedUser(user);
						secretaryScreen.setSize(1763,763);
						secretaryScreen.setVisible(true);
						
					}else 
					JOptionPane.showMessageDialog(null, "Conectarea a esuat. Va rugam reincercati!");
				}
			}
		});
		
	}
	public void closeScreen(){
		this.invalidate();
		this.dispose();
		this.setVisible(false);
		
	}
}

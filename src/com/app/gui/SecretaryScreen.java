package com.app.gui;

import javax.swing.JFrame;

import com.app.db.model.User;

public class SecretaryScreen extends JFrame{
	private User loggedUser;

	public SecretaryScreen(User user) {
		loggedUser = user;
	}

	public void setLoggedUser(User user) {
		this.loggedUser = user;
		
	}

}

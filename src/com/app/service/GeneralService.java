package com.app.service;

import com.app.db.model.RequestType;
import com.app.db.model.SocketRequest;
import com.app.db.model.User;

public class GeneralService {
	
	public User login(String username, String password){
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		SocketRequest sockReq = new SocketRequest(user, RequestType.LOGIN, null, true);
		ComunicationService com = ComunicationService.getInstance();
		SocketRequest response = com.sendRequest(sockReq);
		return (User) response.getParameter();	
	}
	
	
}

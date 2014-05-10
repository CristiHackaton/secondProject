package com.app.service;

import java.util.List;

import com.app.db.model.RequestType;
import com.app.db.model.SocketRequest;
import com.app.db.model.User;

public class AdminService extends GeneralService{

	public void createUser(User loggedUser, User user){
		SocketRequest req = new SocketRequest(loggedUser, RequestType.ADD_USER, user, false);
		ComunicationService.getInstance().sendRequest(req);
	}
	
	public void deleteUser(User loggedUser,User user){
		SocketRequest req = new SocketRequest(loggedUser, RequestType.DELETE_USER, user, false);
		ComunicationService.getInstance().sendRequest(req);
	}
	
	public void updateUser(User loggedUser,User user){
		SocketRequest req = new SocketRequest(loggedUser, RequestType.UPDATE_USER, user, false);
		ComunicationService.getInstance().sendRequest(req);
	}
	
	public List<User> getAllUsers(User loggedUser){
		SocketRequest req = new SocketRequest(loggedUser, RequestType.GET_ALL_USER, null, true);
		SocketRequest resp = ComunicationService.getInstance().sendRequest(req);
		return (List<User>) resp.getParameter();
	}
}

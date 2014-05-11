package com.app.service;

import java.util.List;

import com.app.db.model.Consultation;
import com.app.db.model.Pacient;
import com.app.db.model.RequestType;
import com.app.db.model.SocketRequest;
import com.app.db.model.User;

public class SecretaryService extends GeneralService{

	public void addPaticient(User loggedUser, Pacient pacient){
		SocketRequest req = new SocketRequest(loggedUser, RequestType.ADD_PACIENT, pacient, false);
		ComunicationService.getInstance().sendRequest(req);
	}
	
	public void updatePacient(User loggedUser, Pacient pacient){
		SocketRequest req = new SocketRequest(loggedUser, RequestType.UPDATE_PACIENT, pacient, false);
		ComunicationService.getInstance().sendRequest(req);
	}
	
	public void createConsultation(User loggedUser, Consultation consultation){
		SocketRequest req = new SocketRequest(loggedUser, RequestType.CREATE_CONSULTATION, consultation, false);
		ComunicationService.getInstance().sendRequest(req);
	}
	
	public void deleteConsultation(User loggedUser, Consultation consultation){
		SocketRequest req = new SocketRequest(loggedUser, RequestType.DELETE_CONSULTATION, consultation, false);
		ComunicationService.getInstance().sendRequest(req);
	}
	
	public void updateConsultation(User loggedUser, Consultation consultation){
		SocketRequest req = new SocketRequest(loggedUser, RequestType.UPDATE_CONSULTATION, consultation, false);
		ComunicationService.getInstance().sendRequest(req);
	}
	
	public List<Consultation> getAllConsultations(User loggedUser){
		SocketRequest req = new SocketRequest(loggedUser, RequestType.GET_ALL_CONSULTATIONS, null, true);
		SocketRequest resp = ComunicationService.getInstance().sendRequest(req);
		return (List<Consultation>) resp.getParameter();
	}
	
	public List<Pacient> getAllPatients(User loggedUser){
		SocketRequest req = new SocketRequest(loggedUser, RequestType.GET_ALL_PACIENTI, null, true);
		SocketRequest resp = ComunicationService.getInstance().sendRequest(req);
		return (List<Pacient>) resp.getParameter();		
	}
}

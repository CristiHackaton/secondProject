package com.app.service;

import java.util.List;

import com.app.db.model.Consultation;
import com.app.db.model.Pacient;
import com.app.db.model.RequestType;
import com.app.db.model.SocketRequest;
import com.app.db.model.User;

public class DoctorService extends GeneralService{
	public void addConsultation(User loggedInUser,Consultation consultation){
		SocketRequest req = new SocketRequest(loggedInUser, RequestType.ADD_CONSULTATION_DETAILS, consultation, false);
		ComunicationService.getInstance().sendRequest(req);
	}
	
	public List<Consultation> getAllConsultationsForPacient(User loggedInUser, Pacient pacient){
		SocketRequest req = new SocketRequest(loggedInUser, RequestType.GET_ALL_CONSULTATIONS_PACIENT, pacient, true);
		SocketRequest response = ComunicationService.getInstance().sendRequest(req);
		return (List<Consultation>) response.getParameter();
	}
	
	public List<Pacient> getAllPatients(User loggedInUser){
		SocketRequest req = new SocketRequest(loggedInUser, RequestType.GET_ALL_PACIENTI_FOR_DOCTOR, null, true);
		SocketRequest response = ComunicationService.getInstance().sendRequest(req);
		return (List<Pacient>) response.getParameter();
	}

}

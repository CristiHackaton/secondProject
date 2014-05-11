package com.app.gui;

import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import com.app.db.model.Consultation;
import com.app.db.model.Pacient;

public class ConsultationListModel implements ListModel<Consultation> {
	List <Consultation> listaConsultatii;
	
	public ConsultationListModel(List<Consultation> listaConsultatii){
		this.listaConsultatii=listaConsultatii;
	}

	@Override
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Consultation getElementAt(int arg0) {
		// TODO Auto-generated method stub
		return listaConsultatii.get(arg0);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return listaConsultatii.size();
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub

	}

}

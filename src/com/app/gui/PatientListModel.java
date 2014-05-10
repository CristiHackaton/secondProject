package com.app.gui;

import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import com.app.db.model.Pacient;

public class PatientListModel implements ListModel<Pacient> {
	List <Pacient> listaPacienti;
	public PatientListModel(List<Pacient> listaPacienti){
		this.listaPacienti=listaPacienti;
	}
	
	public List<Pacient> getlistaPacienti(){
		return listaPacienti;
	}
	
	
	@Override
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pacient getElementAt(int arg0) {
		
		return listaPacienti.get(arg0);
	}

	@Override
	public int getSize() {

		return listaPacienti.size();
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

}

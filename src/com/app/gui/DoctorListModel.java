package com.app.gui;

import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import com.app.db.model.Doctor;
import com.app.db.model.Pacient;

public class DoctorListModel implements ListModel<Doctor> {
	List <Doctor> listaDoctor;
	public DoctorListModel(List<Doctor> listaDoctor){
		this.listaDoctor=listaDoctor;
	}

	@Override
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Doctor getElementAt(int arg0) {
		// TODO Auto-generated method stub
		return listaDoctor.get(arg0);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return listaDoctor.size();
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub

	}

}

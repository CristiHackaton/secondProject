package com.app.gui;

import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import com.app.db.model.Pacient;
import com.app.db.model.User;

public class UserListModel implements ListModel<User> {
	List <User> listaUseri;
	public UserListModel(List<User> listaUseri){
		this.listaUseri=listaUseri;
	}
	
	public List<User> getListaUseri(){
		return listaUseri;
	}
	@Override
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public User getElementAt(int arg0) {
		// TODO Auto-generated method stub
		return listaUseri.get(arg0);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return listaUseri.size();
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub

	}

}

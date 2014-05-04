package com.app.db.model;

import java.io.Serializable;

public class SocketRequest implements Model{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User user;
	private String typeOfRequest;
	private Serializable parameter;
	private boolean needsResponse;
	
	
	public SocketRequest(User user, String typeOfRequest, Model parameter, boolean needsResponse) {
		super();
		this.user = user;
		this.typeOfRequest = typeOfRequest;
		this.parameter = parameter;
		this.needsResponse = needsResponse;
	}
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the typeOfRequest
	 */
	public String getTypeOfRequest() {
		return typeOfRequest;
	}
	/**
	 * @param typeOfRequest the typeOfRequest to set
	 */
	public void setTypeOfRequest(String typeOfRequest) {
		this.typeOfRequest = typeOfRequest;
	}
	/**
	 * @return the parameter
	 */
	public Serializable getParameter() {
		return parameter;
	}
	/**
	 * @param parameter the parameter to set
	 */
	public void setParameter(Serializable parameter) {
		this.parameter = parameter;
	}

	/**
	 * @return the needsResponse
	 */
	public boolean isNeedsResponse() {
		return needsResponse;
	}

	/**
	 * @param needsResponse the needsResponse to set
	 */
	public void setNeedsResponse(boolean needsResponse) {
		this.needsResponse = needsResponse;
	}
	

}

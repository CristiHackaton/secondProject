package com.app.db.model;

public class User implements Model{
    private int userID;
    private String username;
    private String email;
    private int userType;
    private String password;
   
    public User() {

    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(final int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(final int userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
    
    
   
    public boolean isDoctor(){
    	
    	if (userType==1){
    		return true;
    	}else return false;
    }
    
    public boolean isAdmin(){
    	if (userType==0){
    		return true;
    	}else return false;
    }
    
    public boolean isSecretary(){
    if (userType==2){
		return true;
	}else return false;
}
    
    public String toString(){
    	return username;
    }
}

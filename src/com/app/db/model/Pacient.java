package com.app.db.model;

import java.util.Date;

public class Pacient implements Model {
    private int id;
    private String name;
    private String cnp;
    private String identitiCard;
    private Date birth;
    private String address;

    public Pacient() {

    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(final String cnp) {
        this.cnp = cnp;
    }

    public String getIdentitiCard() {
        return identitiCard;
    }

    public void setIdentitiCard(final String identitiCard) {
        this.identitiCard = identitiCard;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(final Date birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }
    public String toString(){
    	return name;
    }
}
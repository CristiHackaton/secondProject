package com.app.db.model;

import java.util.Date;

public class Consultation implements Model {
    private Date consultationDate;
    private float duration;
    private int id;
    private Pacient pacient;
    private User doctor;
    private String notes;

    public Consultation() {

    }

    public Date getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(final Date consultationDate) {
        this.consultationDate = consultationDate;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(final float duration) {
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(final Pacient pacient) {
        this.pacient = pacient;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(final User doctor) {
        this.doctor = doctor;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(final String notes) {
        this.notes = notes;
    }
}
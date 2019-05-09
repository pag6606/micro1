package com.krugercorp.ec.micro1.bean;

import java.util.Date;

public class ExceptionResponse {

    private Date timestamp = new Date();
    private String mesage;
    private String details;

    public ExceptionResponse( String mesage, String details) {

        this.mesage = mesage;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMesage() {
        return mesage;
    }

    public void setMesage(String mesage) {
        this.mesage = mesage;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

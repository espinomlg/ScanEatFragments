package com.example.espino.scaneat.models;

/**
 * Created by espino on 17/12/16.
 */

public class Comentary {
    private String username,
    date,
    comentary;

    public Comentary(String username, String date, String comentary) {
        this.username = username;
        this.date = date;
        this.comentary = comentary;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComentary() {
        return comentary;
    }

    public void setComentary(String comentary) {
        this.comentary = comentary;
    }
}

package com.example.agendaapp;

public class Contacto {
    private String nombres;
    private String telf;
    private String email;

    public Contacto(String nombres, String telf, String email) {
        this.nombres = nombres;
        this.telf = telf;
        this.email = email;
    }

    public String getNombres() {
        return nombres;
    }

    public String getTelf() {
        return telf;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return nombres + "," + telf + "," + email;
    }
}

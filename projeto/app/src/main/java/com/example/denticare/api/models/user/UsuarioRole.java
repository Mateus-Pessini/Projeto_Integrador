package com.example.denticare.api.models.user;

public enum UsuarioRole {

    DENTISTA("dentista"),
    SECRETARIA("secretaria");

    private String role;

    UsuarioRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

}

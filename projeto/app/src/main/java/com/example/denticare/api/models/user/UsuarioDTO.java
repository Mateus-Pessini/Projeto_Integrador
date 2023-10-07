package com.example.denticare.api.models.user;


public class UsuarioDTO {

    private Long id;

    private String login;

    private UsuarioRole role;

    private Boolean status;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String login, UsuarioRole role, Boolean status) {
        this.id = id;
        this.login = login;
        this.role = role;
        this.status = status;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public UsuarioRole getRole() {
        return role;
    }

    public void setRole(UsuarioRole role) {
        this.role = role;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

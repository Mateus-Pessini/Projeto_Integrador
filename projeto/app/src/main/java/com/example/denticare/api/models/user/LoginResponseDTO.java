package com.example.denticare.api.models.user;

public class LoginResponseDTO {

    private Long id;
    private String token;
    private String login;
    private String email;
    private UsuarioRole role;

    public UsuarioRole getRole() {
        return role;
    }

    public void setRole(UsuarioRole role) {
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LoginResponseDTO(Long id, String token, String login, String email, UsuarioRole role) {
        this.id = id;
        this.token = token;
        this.login = login;
        this.email = email;
        this.role = role;
    }

    public LoginResponseDTO() {
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

}

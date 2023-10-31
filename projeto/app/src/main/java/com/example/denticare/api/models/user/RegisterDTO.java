package com.example.denticare.api.models.user;


public class RegisterDTO {
    private String login;
    private String senha;
    private UsuarioRole role;

    private Long pessoaId;

    public RegisterDTO() {
    }

    public RegisterDTO(String login, String senha, UsuarioRole role, Long pessoaId) {
        this.login = login;
        this.senha = senha;
        this.role = role;
        this.pessoaId = pessoaId;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsuarioRole getRole() {
        return role;
    }

    public void setRole(UsuarioRole role) {
        this.role = role;
    }
}

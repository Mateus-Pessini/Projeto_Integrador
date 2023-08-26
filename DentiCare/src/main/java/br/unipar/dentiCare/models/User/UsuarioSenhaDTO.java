package br.unipar.dentiCare.models.User;

public class UsuarioSenhaDTO {

    private Long usuarioId;
    private String novaSenha;

    public UsuarioSenhaDTO() {
    }

    public UsuarioSenhaDTO(Long usuarioId, String novaSenha) {
        this.usuarioId = usuarioId;
        this.novaSenha = novaSenha;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }
}

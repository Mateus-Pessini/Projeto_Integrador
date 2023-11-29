package br.unipar.dentiCare.models.Pessoa;

import br.unipar.dentiCare.enums.TpPessoaEnum;

public class PessoaDTO {

    private Long id;

    private String nome;

    private String cpf;

    private String rg;

    private String nrtelefone;

    private String email;

    private String login;

    private String senha;

    private TpPessoaEnum tpPessoa;

    private String cro;

    private String especialidade;

    //private byte ftPerfil;

    private String nmRua;

    private String bairro;

    private int numero;

    private String cep;

    private String complemento;

    private Long cidadeId;


    public PessoaDTO(Long id, String nome, String cpf, String rg, String nrtelefone, String email, String login, String senha, TpPessoaEnum tpPessoa, String cro, String especialidade,// byte ftPerfil,
                     String nmRua, int numero, String bairro, String cep, String complemento, Long cidadeId) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.nrtelefone = nrtelefone;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.tpPessoa = tpPessoa;
        this.cro = cro;
        this.especialidade = especialidade;
        //this.ftPerfil = ftPerfil;
        this.nmRua = nmRua;
        this.bairro = bairro;
        this.numero = numero;
        this.cep = cep;
        this.complemento = complemento;
        this.cidadeId = cidadeId;
    }

    public PessoaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNrtelefone() {
        return nrtelefone;
    }

    public void setNrtelefone(String nrtelefone) {
        this.nrtelefone = nrtelefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TpPessoaEnum getTpPessoa() {
        return tpPessoa;
    }

    public void setTpPessoa(TpPessoaEnum tpPessoa) {
        this.tpPessoa = tpPessoa;
    }

    public String getNmRua() {
        return nmRua;
    }

    public void setNmRua(String nmRua) {
        this.nmRua = nmRua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Long getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Long cidadeId) {
        this.cidadeId = cidadeId;
    }

    public String getCro() {
        return cro;
    }

    public void setCro(String cro) {
        this.cro = cro;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

   /* public byte getFtPerfil() {
        return ftPerfil;
    }

    public void setFtPerfil(byte ftPerfil) {
        this.ftPerfil = ftPerfil;
    }*/

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

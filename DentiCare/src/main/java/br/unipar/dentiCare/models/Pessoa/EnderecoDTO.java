package br.unipar.dentiCare.models.Pessoa;


public class EnderecoDTO {

    private Long id;

    private String nmRua;

    private String bairro;

    private int numero;

    private String CEP;

    private String complemento;

    private Long cidadeId;

    private Long pessoaId;

    public EnderecoDTO() {
    }

    public EnderecoDTO(Long id, String nmRua, String bairro, int numero, String CEP, String complemento, Long cidadeId, Long pessoaId) {
        this.id = id;
        this.nmRua = nmRua;
        this.bairro = bairro;
        this.numero = numero;
        this.CEP = CEP;
        this.complemento = complemento;
        this.cidadeId = cidadeId;
        this.pessoaId = pessoaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmRua() {
        return nmRua;
    }

    public void setNmRua(String nmRua) {
        this.nmRua = nmRua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Long getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Long cidadeId) {
        this.cidadeId = cidadeId;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }
}

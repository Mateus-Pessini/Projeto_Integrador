package br.unipar.dentiCare.models.Pessoa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@DynamicUpdate
@Table(name = "Endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 200)
    private String nmRua;

    @NotBlank
    @NotEmpty
    @NotNull
    private int numero;

    @Size(min = 3, max = 200)
    private String bairro;

    @Size(min = 8, max = 8)
    private String CEP;

    private String complemento;

    @ManyToOne
    @JoinColumn(name = "clienteId")
    @JsonIgnore
    private Cliente cliente;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cidadeId")
    private Cidade cidade;

    public Endereco() {
    }

    public Endereco(Long id, String nmRua, int numero, String bairro, String CEP, String complemento, Cliente cliente, Cidade cidade) {
        this.id = id;
        this.nmRua = nmRua;
        this.numero = numero;
        this.bairro = bairro;
        this.CEP = CEP;
        this.complemento = complemento;
        this.cliente = cliente;
        this.cidade = cidade;
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

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
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

    public Pessoa getPessoa() {
        return cliente;
    }

    public void setPessoa(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}

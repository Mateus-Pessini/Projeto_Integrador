package br.unipar.dentiCare.models.Pessoa;

import br.unipar.dentiCare.enums.TpPessoaEnum;
import br.unipar.dentiCare.models.Consulta.Dentes;
import br.unipar.dentiCare.models.User.Usuario;
import br.unipar.dentiCare.models.User.UsuarioRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "Pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa implements UserDetails {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 100)
    private String nome;

    @Size(min = 11, max = 11)
    private String cpf;

    @Size(min = 1)
    private String senha;

    @Size(min = 9, max = 9)
    private String rg;

    @Size(min = 1, max = 15)
    private String nrtelefone;

    @Size(min = 3, max = 60)
    private String email;

    @Size(min = 3, max = 60)
    private String login;

    private TpPessoaEnum tpPessoa;

    private String cro;

    private String especialidade;

    //@Lob
    //@Basic(fetch = FetchType.LAZY)
    //private byte ftPerfil;

    // Endereço
    @Size(min = 3, max = 200)
    private String nmRua;

    private int numero;

    @Size(min = 3, max = 200)
    private String bairro;

    @Size(min = 8, max = 8)
    private String cep;

    private String complemento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cidadeId")
    @JsonIgnore
    private Cidade cidade;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pessoa_id")
    @JsonIgnore
    private Usuario user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "denteId")
    @JsonIgnore
    private List<Dentes> dentes;

    public Pessoa(Long id, String nome, String cpf, String rg, String nrtelefone, String email, String login, String senha, TpPessoaEnum tpPessoa, String cro, String especialidade,// byte ftPerfil,
                     String nmRua, int numero, String bairro, String cep, String complemento, Cidade cidade) {
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
        this.cidade = cidade;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.tpPessoa == TpPessoaEnum.DENTISTA) return List.of(new SimpleGrantedAuthority("ROLE_DENTISTA"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public TpPessoaEnum getTpPessoa() {
        return tpPessoa;
    }

    public void setTpPessoa(TpPessoaEnum tpPessoa) {
        this.tpPessoa = tpPessoa;
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

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public List<Dentes> getDentes() {
        return dentes;
    }

    public void setDentes(List<Dentes> dentes) {
        this.dentes = dentes;
    }
}

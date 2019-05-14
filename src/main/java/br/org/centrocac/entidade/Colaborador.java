/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.entidade;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author BPMLAB-02
 */
@Entity
@Table(name = "colaborador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Colaborador.findAll", query = "SELECT c FROM Colaborador c"),
    @NamedQuery(name = "Colaborador.findById", query = "SELECT c FROM Colaborador c WHERE c.id = :id"),
    @NamedQuery(name = "Colaborador.findByNome", query = "SELECT c FROM Colaborador c WHERE c.nome = :nome"),
    @NamedQuery(name = "Colaborador.findByEmail", query = "SELECT c FROM Colaborador c WHERE c.email = :email"),
    @NamedQuery(name = "Colaborador.findByCelular", query = "SELECT c FROM Colaborador c WHERE c.celular = :celular"),
    @NamedQuery(name = "Colaborador.findByLogradouro", query = "SELECT c FROM Colaborador c WHERE c.logradouro = :logradouro"),
    @NamedQuery(name = "Colaborador.findByNumero", query = "SELECT c FROM Colaborador c WHERE c.numero = :numero"),
    @NamedQuery(name = "Colaborador.findByComplemento", query = "SELECT c FROM Colaborador c WHERE c.complemento = :complemento"),
    @NamedQuery(name = "Colaborador.findByBairro", query = "SELECT c FROM Colaborador c WHERE c.bairro = :bairro"),
    @NamedQuery(name = "Colaborador.findByCep", query = "SELECT c FROM Colaborador c WHERE c.cep = :cep"),
    @NamedQuery(name = "Colaborador.findByCpfOuCnpj", query = "SELECT c FROM Colaborador c WHERE c.cpfOuCnpj = :cpfOuCnpj"),
    @NamedQuery(name = "Colaborador.findBySenha", query = "SELECT c FROM Colaborador c WHERE c.senha = :senha"),
    @NamedQuery(name = "Colaborador.findByPerfil", query = "SELECT c FROM Colaborador c WHERE c.perfil = :perfil"),
    @NamedQuery(name = "Colaborador.findByProfissao", query = "SELECT c FROM Colaborador c WHERE c.profissao = :profissao"),
    @NamedQuery(name = "Colaborador.findByComoColaborar", query = "SELECT c FROM Colaborador c WHERE c.comoColaborar = :comoColaborar")})
    //, @NamedQuery(name = "Colaborador.findByConfirmacao", query = "SELECT c FROM Colaborador c WHERE c.confirmacao = :confirmacao")})
public class Colaborador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nome")
    private String nome;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "celular")
    private String celular;
    @Size(max = 200)
    @Column(name = "logradouro")
    private String logradouro;
    @Size(max = 45)
    @Column(name = "numero")
    private String numero;
    @Size(max = 45)
    @Column(name = "complemento")
    private String complemento;
    @Size(max = 100)
    @Column(name = "bairro")
    private String bairro;
    @Size(max = 10)
    @Column(name = "cep")
    private String cep;
    @Size(max = 18)
    @Column(name = "cpfOuCnpj")
    private String cpfOuCnpj;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "senha")
    private String senha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "perfil")
    private Character perfil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "profissao")
    private String profissao;
    @Size(max = 250)
    @Column(name = "comoColaborar")
    private String comoColaborar;
    /*@Size(max = 10)
     @Column(name = "confirmacao")
     private String confirmacao;*/
    @OneToMany(mappedBy = "colaborador")
    private List<Doacao> doacaoList;

    @ManyToMany
    @JoinTable(name = "colaborador_acao", joinColumns
            = {
                @JoinColumn(name = "colaborador_id")}, inverseJoinColumns
            = {
                @JoinColumn(name = "acao_id")})
    private List<Acao> acaoList;

    public Colaborador() {
    }

    public Colaborador(Integer id) {
        this.id = id;
    }

    public Colaborador(Integer id, String nome, String email, String celular, String senha, Character perfil, String profissao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.senha = senha;
        this.perfil = perfil;
        this.profissao = profissao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Character getPerfil() {
        return perfil;
    }

    public void setPerfil(Character perfil) {
        this.perfil = perfil;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getComoColaborar() {
        return comoColaborar;
    }

    public void setComoColaborar(String comoColaborar) {
        this.comoColaborar = comoColaborar;
    }

    /*public String getConfirmacao() {
     return confirmacao;
     }

     public void setConfirmacao(String confirmacao) {
     this.confirmacao = confirmacao;
     }*/
    @XmlTransient
    public List<Doacao> getDoacaoList() {
        return doacaoList;
    }

    public void setDoacaoList(List<Doacao> doacaoList) {
        this.doacaoList = doacaoList;
    }

    public List<Acao> getAcaoList() {
        return acaoList;
    }

    public void setAcaoList(List<Acao> acaoList) {
        this.acaoList = acaoList;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.nome);
        hash = 67 * hash + Objects.hashCode(this.email);
        hash = 67 * hash + Objects.hashCode(this.celular);
        hash = 67 * hash + Objects.hashCode(this.logradouro);
        hash = 67 * hash + Objects.hashCode(this.numero);
        hash = 67 * hash + Objects.hashCode(this.complemento);
        hash = 67 * hash + Objects.hashCode(this.bairro);
        hash = 67 * hash + Objects.hashCode(this.cep);
        hash = 67 * hash + Objects.hashCode(this.cpfOuCnpj);
        hash = 67 * hash + Objects.hashCode(this.senha);
        hash = 67 * hash + Objects.hashCode(this.perfil);
        hash = 67 * hash + Objects.hashCode(this.profissao);
        hash = 67 * hash + Objects.hashCode(this.comoColaborar);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Colaborador other = (Colaborador) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.celular, other.celular)) {
            return false;
        }
        if (!Objects.equals(this.logradouro, other.logradouro)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        if (!Objects.equals(this.complemento, other.complemento)) {
            return false;
        }
        if (!Objects.equals(this.bairro, other.bairro)) {
            return false;
        }
        if (!Objects.equals(this.cep, other.cep)) {
            return false;
        }
        if (!Objects.equals(this.cpfOuCnpj, other.cpfOuCnpj)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.perfil, other.perfil)) {
            return false;
        }
        if (!Objects.equals(this.profissao, other.profissao)) {
            return false;
        }
        if (!Objects.equals(this.comoColaborar, other.comoColaborar)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "Colaborador{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", celular=" + celular + ", logradouro=" + logradouro + ", numero=" + numero + ", complemento=" + complemento + ", bairro=" + bairro + ", cep=" + cep + ", cpfOuCnpj=" + cpfOuCnpj + ", senha=" + senha + ", perfil=" + perfil + ", profissao=" + profissao + ", comoColaborar=" + comoColaborar + ", doacaoList=" + doacaoList + ", acaoList=" + acaoList + '}';
    }

}

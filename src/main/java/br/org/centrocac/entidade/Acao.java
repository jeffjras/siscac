/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BPMLAB-02
 */
@Entity
@Table(name = "acao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acao.findAll", query = "SELECT a FROM Acao a"),
    @NamedQuery(name = "Acao.findById", query = "SELECT a FROM Acao a WHERE a.id = :id"),
    @NamedQuery(name = "Acao.findByNome", query = "SELECT a FROM Acao a WHERE a.nome = :nome"),
    @NamedQuery(name = "Acao.findByDescricao", query = "SELECT a FROM Acao a WHERE a.descricao = :descricao"),
    @NamedQuery(name = "Acao.findByInicio", query = "SELECT a FROM Acao a WHERE a.inicio = :inicio"),
    @NamedQuery(name = "Acao.findByFim", query = "SELECT a FROM Acao a WHERE a.fim = :fim"),
    @NamedQuery(name = "Acao.findByCadastro", query = "SELECT a FROM Acao a WHERE a.cadastro = :cadastro"),
    @NamedQuery(name = "Acao.findByCargaHoraria", query = "SELECT a FROM Acao a WHERE a.cargaHoraria = :cargaHoraria")})
public class Acao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fim;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cadastro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cargaHoraria")
    private int cargaHoraria;

    @ManyToMany(mappedBy = "acaoList")
    private List<Colaborador> voluntariosList;

    public Acao() {
    }

    public Acao(Integer id) {
        this.id = id;
    }

    public Acao(Integer id, String nome, String descricao, Date inicio, Date fim, Date cadastro, int cargaHoraria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.inicio = inicio;
        this.fim = fim;
        this.cadastro = cadastro;
        this.cargaHoraria = cargaHoraria;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public Date getCadastro() {
        return cadastro;
    }

    public void setCadastro(Date cadastro) {
        this.cadastro = cadastro;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public List<Colaborador> getVoluntariosList() {
        return voluntariosList;
    }

    public void setVoluntariosList(List<Colaborador> voluntariosList) {
        this.voluntariosList = voluntariosList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.nome);
        hash = 59 * hash + Objects.hashCode(this.descricao);
        hash = 59 * hash + Objects.hashCode(this.inicio);
        hash = 59 * hash + Objects.hashCode(this.fim);
        hash = 59 * hash + Objects.hashCode(this.cadastro);
        hash = 59 * hash + this.cargaHoraria;
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
        final Acao other = (Acao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.inicio, other.inicio)) {
            return false;
        }
        if (!Objects.equals(this.fim, other.fim)) {
            return false;
        }
        if (!Objects.equals(this.cadastro, other.cadastro)) {
            return false;
        }
        if (this.cargaHoraria != other.cargaHoraria) {
            return false;
        }
        return true;
    }

    
    

    @Override
    public String toString() {
        return "Acao{" + "id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", inicio=" + inicio + ", fim=" + fim + ", cadastro=" + cadastro + ", cargaHoraria=" + cargaHoraria + ", voluntariosList=" + voluntariosList + '}';
    }

}

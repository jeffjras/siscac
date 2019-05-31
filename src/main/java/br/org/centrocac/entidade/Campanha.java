/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author BPMLAB-02
 */
@Entity
@Table(name = "campanha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Campanha.findAll", query = "SELECT c FROM Campanha c"),
    @NamedQuery(name = "Campanha.findById", query = "SELECT c FROM Campanha c WHERE c.id = :id"),
    @NamedQuery(name = "Campanha.findByNome", query = "SELECT c FROM Campanha c WHERE c.nome = :nome"),
    @NamedQuery(name = "Campanha.findByDescricao", query = "SELECT c FROM Campanha c WHERE c.descricao = :descricao"),
    @NamedQuery(name = "Campanha.findByCadastro", query = "SELECT c FROM Campanha c WHERE c.cadastro = :cadastro"),
    @NamedQuery(name = "Campanha.findByHabilitada", query = "SELECT c FROM Campanha c WHERE c.habilitada = :habilitada"),
    @NamedQuery(name = "Campanha.findByDataFim", query = "SELECT c FROM Campanha c WHERE c.dataFim = :dataFim"),
    @NamedQuery(name = "Campanha.findByMeta", query = "SELECT c FROM Campanha c WHERE c.meta = :meta"),
    @NamedQuery(name = "Campanha.findByDoacaoMinima", query = "SELECT c FROM Campanha c WHERE c.doacaoMinima = :doacaoMinima"),
    @NamedQuery(name = "Campanha.findByArrecadado", query = "SELECT c FROM Campanha c WHERE c.arrecadado = :arrecadado")})
public class Campanha implements Serializable {

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
    @Column(name = "cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cadastro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "habilitada")
    private boolean habilitada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataFim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFim;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "meta")
    private BigDecimal meta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "doacaoMinima")
    private BigDecimal doacaoMinima;
    @Basic(optional = false)
    @NotNull
    @Column(name = "arrecadado")
    private BigDecimal arrecadado;
    @OneToMany(mappedBy = "campanha")
    private List<ItemDoacao> itemDoacaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campanha")
    private List<ItemCampanha> itemCampanhaList;

    @ManyToMany
    @JoinTable(name = "campanha_acao", joinColumns
            = {
                @JoinColumn(name = "campanha_id")}, inverseJoinColumns
            = {
                @JoinColumn(name = "acao_id")})
    private List<Acao> acaoList;

    public Campanha() {
    }

    public Campanha(Integer id) {
        this.id = id;
    }

    public Campanha(Integer id, String nome, String descricao, Date cadastro, boolean habilitada, Date dataFim, BigDecimal meta, BigDecimal doacaoMinima, BigDecimal arrecadado) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.cadastro = cadastro;
        this.habilitada = habilitada;
        this.dataFim = dataFim;
        this.meta = meta;
        this.doacaoMinima = doacaoMinima;
        this.arrecadado = arrecadado;
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

    public Date getCadastro() {
        return cadastro;
    }

    public void setCadastro(Date cadastro) {
        this.cadastro = cadastro;
    }

    public boolean getHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public BigDecimal getMeta() {
        return meta;
    }

    public void setMeta(BigDecimal meta) {
        this.meta = meta;
    }

    public BigDecimal getDoacaoMinima() {
        return doacaoMinima;
    }

    public void setDoacaoMinima(BigDecimal doacaoMinima) {
        this.doacaoMinima = doacaoMinima;
    }

    public BigDecimal getArrecadado() {
        return arrecadado;
    }

    public void setArrecadado(BigDecimal arrecadado) {
        this.arrecadado = arrecadado;
    }

    @XmlTransient
    public List<ItemDoacao> getItemDoacaoList() {
        return itemDoacaoList;
    }

    public void setItemDoacaoList(List<ItemDoacao> itemDoacaoList) {
        this.itemDoacaoList = itemDoacaoList;
    }

    @XmlTransient
    public List<ItemCampanha> getItemCampanhaList() {
        return itemCampanhaList;
    }

    public void setItemCampanhaList(List<ItemCampanha> itemCampanhaList) {
        this.itemCampanhaList = itemCampanhaList;
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
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.nome);
        hash = 41 * hash + Objects.hashCode(this.descricao);
        hash = 41 * hash + Objects.hashCode(this.cadastro);
        hash = 41 * hash + (this.habilitada ? 1 : 0);
        hash = 41 * hash + Objects.hashCode(this.dataFim);
        hash = 41 * hash + Objects.hashCode(this.meta);
        hash = 41 * hash + Objects.hashCode(this.doacaoMinima);
        hash = 41 * hash + Objects.hashCode(this.arrecadado);
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
        final Campanha other = (Campanha) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.cadastro, other.cadastro)) {
            return false;
        }
        if (this.habilitada != other.habilitada) {
            return false;
        }
        if (!Objects.equals(this.dataFim, other.dataFim)) {
            return false;
        }
        if (!Objects.equals(this.meta, other.meta)) {
            return false;
        }
        if (!Objects.equals(this.doacaoMinima, other.doacaoMinima)) {
            return false;
        }
        if (!Objects.equals(this.arrecadado, other.arrecadado)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Campanha{" + "id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", cadastro=" + cadastro + ", habilitada=" + habilitada + ", dataFim=" + dataFim + ", meta=" + meta + ", doacaoMinima=" + doacaoMinima + ", arrecadado=" + arrecadado + ", itemDoacaoList=" + itemDoacaoList + ", itemCampanhaList=" + itemCampanhaList + ", acaoList=" + acaoList + '}';
    }

}

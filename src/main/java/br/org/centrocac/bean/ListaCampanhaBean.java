/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.bean;

import br.org.centrocac.dao.CampanhaDAO;
import br.org.centrocac.dao.ColaboradorDAO;
import br.org.centrocac.entidade.Campanha;
import br.org.centrocac.entidade.Colaborador;
import br.org.centrocac.rn.CampanhaRN;
import br.org.centrocac.util.MailUtil;
import br.org.centrocac.util.UtilBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author ANDRE
 */
@ManagedBean
@ViewScoped
public class ListaCampanhaBean implements Serializable {

    List<Campanha> campanhas = new ArrayList<>();
    transient private CampanhaDAO campanhaDao = new CampanhaDAO();
    transient private ColaboradorDAO colaboradorDao = new ColaboradorDAO();
    private Date dataInicio = null, dataFim = null;
    private Boolean ativo = true;
    private CampanhaRN campanhaRN = new CampanhaRN();

    public List<Campanha> listarTodos() {

        if (dataInicio != null && dataFim != null || ativo == false) {
            campanhas = campanhaRN.obter(dataInicio, dataFim, ativo);
            return campanhas;
        }  else {
            campanhas = campanhaRN.obterTodos();
            return campanhas;
        }
    }

    public String goToEditar() {
        return "campanha.xhtml?faces-redirect=true";
    }

    public String novaCampanha() {
        return "campanha.xhtml?faces-redirect=true";
    }

    public void divulgarCampanha(Campanha c) {
        MailUtil sender = new MailUtil();
        List<Colaborador> colaboradores = colaboradorDao.obterTodos(Colaborador.class);
        if (!colaboradores.isEmpty()) {
            for (Colaborador colaboradore : colaboradores) {
                sender.sendEmailTemplateDivulgacaoCamapanha(colaboradore, c);
            }
            UtilBean.criarMensagemDeInformacao("Emails enviados para os colaboradores! colabs N°: " + colaboradores.size());
        }

    }

    public List<Campanha> getCampanhas() {
        return campanhas;
    }

    public void setCampanhas(List<Campanha> campanhas) {
        this.campanhas = campanhas;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

}

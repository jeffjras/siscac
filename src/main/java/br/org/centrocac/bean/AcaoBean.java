/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.bean;

import br.org.centrocac.entidade.Acao;
import br.org.centrocac.rn.AcaoRN;
import br.org.centrocac.util.UtilBean;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class AcaoBean {
    
    //Entidade
    private Acao entidade = new Acao();
    private List<Acao> entidadeList = new ArrayList<>();

    //RN
    private final AcaoRN ACAO_RN = new AcaoRN();

    // AUX
    private String outcome;

    @PostConstruct
    private void posInit() {
        outcome = "acaoFormBackup.xhtml?faces-redirect=true";
        entidadeList = ACAO_RN.obterTodos();
    }

    public String excluir() {
        if (ACAO_RN.excluir(entidade)) {
            UtilBean.criarMensagemDeAviso("Ação excluído com sucesso!");
            return null;
        } else {
            UtilBean.criarMensagemDeErro("Ação excluído com sucesso!");
            return null;
        }
    }

    public String goToEditar() {
        UtilBean.naSessao("id", entidade.getId());
        return outcome;
    }

    public String goToVincularColaborador() {
        outcome = "vincularColaboradorAAcao.xhtml?faces-redirect=true";
        UtilBean.naSessao("id", entidade.getId());
        return outcome;
    }
    
    public void limparCampos() {
        this.entidade = new Acao();
    }

    public Acao getEntidade() {
        return entidade;
    }

    public void setEntidade(Acao entidade) {
        this.entidade = entidade;
    }

    public List<Acao> getEntidadeList() {
        return entidadeList;
    }

    public void setEntidadeList(List<Acao> entidadeList) {
        this.entidadeList = entidadeList;
    }

}

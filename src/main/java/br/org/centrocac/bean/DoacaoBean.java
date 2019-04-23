/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.bean;

import br.org.centrocac.entidade.Doacao;
import br.org.centrocac.rn.DoacaoRN;
import br.org.centrocac.util.UtilBean;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author toshiaki
 */
public class DoacaoBean {

    //Entidade

    private Doacao entidade = new Doacao();
    private List<Doacao> entidadeList = new ArrayList<>();

    //RN
    private final DoacaoRN rn = new DoacaoRN();

    // AUX
    private String outcome;

    @PostConstruct
    private void posInit() {
        outcome = "doacao-gerenciamento.xhtml?faces-redirect=true";
        entidadeList = rn.obterTodos();
    }

    public String excluir() {
        if (rn.excluir(entidade)) {
            UtilBean.criarMensagemDeAviso("Doação excluída com sucesso!");
            return null;
        } else {
            UtilBean.criarMensagemDeErro("Doação excluída com sucesso!");
            return null;
        }
    }

    public String goToEditar() {
        UtilBean.naSessao("id", entidade.getId());
        return outcome;
    }

    public void limparCampos() {
        this.entidade = new Doacao();
    }

    public Doacao getEntidade() {
        return entidade;
    }

    public void setEntidade(Doacao entidade) {
        this.entidade = entidade;
    }

    public List<Doacao> getEntidadeList() {
        return entidadeList;
    }

    public void setEntidadeList(List<Doacao> entidadeList) {
        this.entidadeList = entidadeList;
    }
}

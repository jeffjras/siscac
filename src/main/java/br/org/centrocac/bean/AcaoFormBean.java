/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.bean;

import br.org.centrocac.entidade.Acao;
import br.org.centrocac.rn.AcaoRN;
import br.org.centrocac.util.UtilBean;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author toshiaki
 */
@ManagedBean
@RequestScoped
public class AcaoFormBean {

    //Entidade
    private Acao entidade = new Acao();

    //RN
    private final AcaoRN RN = new AcaoRN();

    // AUX
    private String outcome;

    private Object id;

    @PostConstruct
    private void posInit() {
        id = UtilBean.daSessao("id");
        if (id != null) {
            findOne((Integer) id);
        }
        outcome = "acao-gerenciamento.xhtml?faces-redirect=true";
    }

    public void findOne(Integer id) {
        entidade = RN.obter(id);
    }

    public String salvar() {
        if (entidade.getId() == null) {
            entidade.setCadastro(new Date());
        }
        if (RN.salvar(entidade)) {
            UtilBean.criarMensagemDeAviso("Cadastro efetudo com sucesso!");
            limparCampos();
            return outcome;
        } else {
            UtilBean.criarMensagemDeErro("Não foi possível efetuar o cadastro!");
            return null;
        }
    }

    public String cancelar() {
        limparCampos();
        return outcome;
    }

    public void limparCampos() {
        entidade = new Acao();
        UtilBean.removerDaSessao("id");
    }

    public Acao getEntidade() {
        return entidade;
    }

    public void setEntidade(Acao entidade) {
        this.entidade = entidade;
    }

}

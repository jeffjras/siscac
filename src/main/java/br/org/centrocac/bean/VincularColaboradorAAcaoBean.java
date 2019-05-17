/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.bean;

import br.org.centrocac.entidade.Acao;
import br.org.centrocac.entidade.Colaborador;
import br.org.centrocac.rn.AcaoRN;
import br.org.centrocac.rn.ColaboradorRN;
import br.org.centrocac.util.UtilBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author toshiaki
 */
@ManagedBean
@ViewScoped
public class VincularColaboradorAAcaoBean {

    //Entidade
    private Acao entidade = new Acao();
    private Colaborador colaboradorSelecionado;
    private List<Colaborador> colaboradorList = new ArrayList<>();

    //RN
    private final AcaoRN RN = new AcaoRN();
    private final ColaboradorRN COLABORADOR_RN = new ColaboradorRN();

    // AUX
    private String outcome;

    private Object id;

    @PostConstruct
    private void posInit() {
        id = UtilBean.daSessao("id");
        if (id != null) {
            findOne((Integer) id);
            findColaboradoresDisponiveis((Integer) id);
        }
        outcome = "listarAcaoBackup.xhtml?faces-redirect=true";
    }

    public void findOne(Integer id) {
        entidade = RN.obter(id);
    }

    private void findColaboradoresDisponiveis(Integer id) {
        colaboradorList = COLABORADOR_RN.obterTodosColaboradoresDisponiveisParACAO(id);
    }

//    public String salvar() {
//        entidade.setVoluntariosList(colaboradorList);
//        if (RN.salvar(entidade)) {
//            UtilBean.criarMensagemDeAviso("Operação efetuda com sucesso!");
//            limparCampos();
//            return outcome;
//        } else {
//            UtilBean.criarMensagemDeErro("Não foi possível efetuar a operação!");
//            return null;
//        }
//    }
//
//    public String cancelar() {
//        limparCampos();
//        return outcome;
//    }

    public void limparCampos() {
        entidade = new Acao();
        UtilBean.removerDaSessao("id");
    }

    public void addColaborador() {

        if (colaboradorSelecionado == null) {
            UtilBean.criarMensagemDeInformacao("Selecione um colaborador");
        } else {
            if (!colaboradorjaAdicionado(colaboradorSelecionado)) {
                colaboradorSelecionado.getAcaoList().add(entidade);
                entidade.getVoluntariosList().add(colaboradorSelecionado);
                entidade.setVoluntariosList(entidade.getVoluntariosList());
                if (COLABORADOR_RN.salvar(colaboradorSelecionado)) {
                    UtilBean.criarMensagemDeInformacao("colaborador adicionado com sucesso!");
                } else {
                    UtilBean.criarMensagemDeErro("Não foi possível efetuar a operação!");
                }
            } else {
                UtilBean.criarMensagemDeAviso("Colaborador ja na lista");
            }
        }
    }

    public void removerColaborador(Colaborador c) {
        if (entidade.getVoluntariosList().remove(c)) {
            c.getAcaoList().remove(entidade);
            
            if (COLABORADOR_RN.salvar(c)) {
                UtilBean.criarMensagemDeInformacao("colaborador removido com sucesso!");
            } else {
                UtilBean.criarMensagemDeErro("Não foi possível efetuar a operação!");
            }
        }

    }

    private boolean colaboradorjaAdicionado(Colaborador c) {
        return entidade.getVoluntariosList().contains(c);
    }

    public Acao getEntidade() {
        return entidade;
    }

    public void setEntidade(Acao entidade) {
        this.entidade = entidade;
    }

    public Colaborador getColaboradorSelecionado() {
        return colaboradorSelecionado;
    }

    public void setColaboradorSelecionado(Colaborador colaboradorSelecionado) {
        this.colaboradorSelecionado = colaboradorSelecionado;
    }

    public List<Colaborador> getColaboradorList() {
        return colaboradorList;
    }

    public void setColaboradorList(List<Colaborador> colaboradorList) {
        this.colaboradorList = colaboradorList;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.bean;

import br.org.centrocac.entidade.Acao;
import br.org.centrocac.entidade.Campanha;
import br.org.centrocac.rn.AcaoRN;
import br.org.centrocac.rn.CampanhaRN;
import br.org.centrocac.rn.ColaboradorRN;
import br.org.centrocac.util.UtilBean;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author toshiaki
 */
@ManagedBean
@ViewScoped
public class VincularCampanhaAAcaoBean {

    //Entidade

    private Campanha entidade = new Campanha();
    private Acao acaoSelecionada;
    private List<Acao> acaoList = new ArrayList<>();

    //RN
    private final AcaoRN RN = new AcaoRN();
    private final ColaboradorRN COLABORADOR_RN = new ColaboradorRN();
    private final CampanhaRN CAMPANHA_RN = new CampanhaRN();

    // AUX
    private String outcome;

    private Object id;

    @PostConstruct
    private void posInit() {
        id = UtilBean.daSessao("id");
        if (id != null) {
            findOne((Integer) id);
            findAcoesDisponiveis((Integer) id);
        }
        outcome = "listarCampanha.xhtml?faces-redirect=true";
    }

    public void findOne(Integer id) {
        entidade = CAMPANHA_RN.obter(id);
    }

    private void findAcoesDisponiveis(Integer id) {
        acaoList = RN.obterTodasAcoesDisponiveisParaCampanha(id);
    }

    public void limparCampos() {
        entidade = new Campanha();
        UtilBean.removerDaSessao("id");
    }

    public void addAcao() {

        if (acaoSelecionada == null) {
            UtilBean.criarMensagemDeInformacao("Selecione um colaborador");
        } else {
            if (!acaoJaAdicionado(acaoSelecionada)) {
                acaoSelecionada.getCampanhasList().add(entidade);
                entidade.getAcaoList().add(acaoSelecionada);
                entidade.setAcaoList(entidade.getAcaoList());
                if (CAMPANHA_RN.salvar(entidade)) {
                    UtilBean.criarMensagemDeInformacao("Ação adicionado com sucesso!");
                } else {
                    UtilBean.criarMensagemDeErro("Não foi possível efetuar a operação!");
                }
            } else {
                UtilBean.criarMensagemDeAviso("Ação ja na lista");
            }
        }
    }

    public void removerAcao(Acao a) {
        if (entidade.getAcaoList().remove(a)) {
            a.getCampanhasList().remove(entidade);

            if (CAMPANHA_RN.salvar(entidade)) {
                UtilBean.criarMensagemDeInformacao("Ação removido com sucesso!");
            } else {
                UtilBean.criarMensagemDeErro("Não foi possível efetuar a operação!");
            }
        }

    }

    private boolean acaoJaAdicionado(Acao a) {
        return entidade.getAcaoList().contains(a);
    }

    public Campanha getEntidade() {
        return entidade;
    }

    public void setEntidade(Campanha entidade) {
        this.entidade = entidade;
    }

    public Acao getAcaoSelecionada() {
        return acaoSelecionada;
    }

    public void setAcaoSelecionada(Acao acaoSelecionada) {
        this.acaoSelecionada = acaoSelecionada;
    }

    public List<Acao> getAcaoList() {
        return acaoList;
    }

    public void setAcaoList(List<Acao> acaoList) {
        this.acaoList = acaoList;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.bean;

import br.org.centrocac.entidade.Colaborador;
import br.org.centrocac.entidade.Doacao;
import br.org.centrocac.rn.ColaboradorRN;
import br.org.centrocac.rn.DoacaoRN;
import br.org.centrocac.util.UtilBean;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
public class DoacaoBean {

    //Atributos
    private Date cadastro = new Date();
    private Integer idColaborador = 0;
    private BigDecimal total = new BigDecimal(0.00);

    //Entidade
    private Doacao entidade = new Doacao();
    private Doacao entidadeSelecionada = new Doacao();
    private List<Doacao> entidadeList = new ArrayList<>();

    //Colaborador
    private Colaborador colaborador = new Colaborador();

    //RN
    private final DoacaoRN rn = new DoacaoRN();
    private ColaboradorRN colaboradorRN = new ColaboradorRN();

    // AUX
    private String outcome;

    @PostConstruct
    private void posInit() {

        this.buscarColaborador();

        this.buscarDoacaoPorColaboradorCadastro();

        outcome = "doacao-gerenciamento.xhtml?faces-redirect=true";
        entidadeList = rn.obterTodos();
    }

    private void buscarColaborador() {
        colaborador = UtilBean.obterContaLogada();
    }

    public void salvarSeIdVazio() {
        entidade.setTotal(total);
        System.out.println("salvar se vazio...");
        System.out.println("data: " + entidade.getCadastro());
        System.out.println("colaborador: " + entidade.getColaborador().getNome());
        System.out.println("total: " + entidade.getTotal().toString());
        if (entidade.getId().equals(0)) {
            System.out.println("id vazio entra na condição");

            if (!rn.salvar(entidade)) {
                UtilBean.criarMensagemDeErro("Não foi possível efetuar  a operação");
            }
        }
    }

    private void buscarDoacaoPorColaboradorCadastro() {
        Boolean existeDoacao = rn.existeDoacao(UtilBean.obterContaLogada().getId(), cadastro);
        System.out.println("existe doacao: " + existeDoacao.toString());

        if (existeDoacao) {
            entidadeSelecionada = rn.obeterPorColaboradorCadastro(UtilBean.obterContaLogada().getId(), cadastro);
            System.out.println("entidade doacao do banco");
            entidade = entidadeSelecionada;            
        } else {
            System.out.println("entidade doacao zerada");
            entidade.setCadastro(cadastro);
            entidade.setColaborador(colaborador);
            entidade.setTotal(total);
            rn.salvar(entidade);
        }

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

    public String goToItemDoacao() {
        return "itemDoacao.xhtml?faces-redirect=true";
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

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

}

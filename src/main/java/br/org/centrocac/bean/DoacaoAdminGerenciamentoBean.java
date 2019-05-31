/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.bean;

import br.org.centrocac.entidade.Colaborador;
import br.org.centrocac.entidade.Doacao;
import br.org.centrocac.entidade.ItemDoacao;
import br.org.centrocac.rn.DoacaoRN;
import br.org.centrocac.util.UtilBean;
import java.util.ArrayList;
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
public class DoacaoAdminGerenciamentoBean {

    //atributos
    private Integer idDoacao;

    //entidade
    private Doacao doacao = new Doacao();
    private Colaborador colaborador = new Colaborador();

    //RN
    private DoacaoRN doacaoRN = new DoacaoRN();

    //List
    private List<Doacao> doacoes = new ArrayList();

    @PostConstruct
    public void init() {
        this.buscarColaborador();
        this.listarDoacoes();
    }

    public void listarDoacoes() {
        doacoes = doacaoRN.obterTodos();
    }

    private void buscarColaborador() {
        colaborador = UtilBean.obterContaLogada();
    }

    public List<ItemDoacao> itensDoacaoLista() {
        List<ItemDoacao> itens = new ArrayList();
        if (idDoacao == null) {
            return itens;
        } else {
            Doacao doacaoTemp = doacaoRN.obter(idDoacao);
            itens = doacaoTemp.getItemDoacaoList();
            return itens;
        }
    }

    public Integer getIdDoacao() {
        return idDoacao;
    }

    public void setIdDoacao(Integer idDoacao) {
        this.idDoacao = idDoacao;
    }

    public List<Doacao> getDoacoes() {
        return doacoes;
    }

}

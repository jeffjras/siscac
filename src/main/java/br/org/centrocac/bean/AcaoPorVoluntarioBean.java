/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.bean;

import br.org.centrocac.entidade.Acao;
import br.org.centrocac.entidade.Colaborador;
import br.org.centrocac.rn.AcaoRN;
import br.org.centrocac.util.MailUtil;
import br.org.centrocac.util.UtilBean;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class AcaoPorVoluntarioBean {

    //Entidade
    private Colaborador entidade;
    private List<Acao> entidadeList = new ArrayList<>();

    //RN
    private final AcaoRN RN = new AcaoRN();
    private final MailUtil mail = new MailUtil();
    // AUX
    private String outcome;

    @PostConstruct
    private void posInit() {
        filtrarPorColaborador();
        outcome = "vincularAcaoAColaborador.xhtml?faces-redirect=true";
    }

    public void limparCampos() {
        this.entidade = new Colaborador();
    }

    public String filtrarPorColaborador() {
        List<Acao> resposta = RN.buscarTodosAcaoPorVoluntario(entidade);
        setEntidadeList(resposta);
        return null;
    }

    public String goToEditar() {
        UtilBean.naSessao("id", entidade.getId());
        return outcome;
    }

    public Colaborador getEntidade() {
        return entidade;
    }

    public void setEntidade(Colaborador entidade) {
        this.entidade = entidade;
    }

    public List<Acao> getEntidadeList() {
        return entidadeList;
    }

    public void setEntidadeList(List<Acao> entidadeList) {
        this.entidadeList = entidadeList;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.bean;

import br.org.centrocac.entidade.Acao;
import br.org.centrocac.entidade.Colaborador;
import br.org.centrocac.rn.ColaboradorRN;
import br.org.centrocac.util.MailUtil;
import br.org.centrocac.util.RelatorioUtil;
import java.util.ArrayList;
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
public class VoluntarioPorAcaoBean {

    //Entidade
    private Acao entidade;
    private List<Colaborador> entidadeList = new ArrayList<>();

    //RN
    private final ColaboradorRN RN = new ColaboradorRN();
    private final MailUtil mail = new MailUtil();
    // AUX
    private String outcome;

    @PostConstruct
    private void posInit() {
        filtrarPorAcao();
    }

    public void limparCampos() {
        this.entidade = new Acao();
    }

    public String filtrarPorAcao() {
        System.out.println("Entrou");
        List<Colaborador> resposta = RN.obterTodosVoluntariosPorAcao(entidade);
        setEntidadeList(resposta);
        System.out.println(getEntidadeList());
        return null;
    }

    public void gerarPdf() {
        RelatorioUtil reportGenerator = new RelatorioUtil();
        reportGenerator.gerarPDF(1);
    }
    public Acao getEntidade() {
        return entidade;
    }

    public void setEntidade(Acao entidade) {
        this.entidade = entidade;
    }

    public List<Colaborador> getEntidadeList() {
        return entidadeList;
    }

    public void setEntidadeList(List<Colaborador> entidadeList) {
        this.entidadeList = entidadeList;
    }
}

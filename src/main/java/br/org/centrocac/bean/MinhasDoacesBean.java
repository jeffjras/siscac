/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.bean;

import br.org.centrocac.entidade.Colaborador;
import br.org.centrocac.entidade.Doacao;
import br.org.centrocac.rn.DoacaoRN;
import br.org.centrocac.util.UtilBean;
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
public class MinhasDoacesBean {
    
    //atributos
    private Date dataInicio;
    private Date dataFim;
    
    //entidade
    private Colaborador colaborador = new Colaborador();
    
    //Lista
    private List<Doacao> minhasDoacoes = new ArrayList();
    
    //RN
    private DoacaoRN doacaoRN = new DoacaoRN();
    
    @PostConstruct
    public void init(){
        this.buscarColaborador();
        
        minhasDoacoes = this.buscaMinhasDoacoes();
        
    }
    
    public void pesquisarDoacoesEntreDatas(){
        if (dataInicio == null && dataFim == null) {
            minhasDoacoes = doacaoRN.obterTodos();
        }else if(dataInicio == null){
            UtilBean.criarMensagemDeAviso("Data de Início está Vazia");
        } else if (dataFim == null){
            UtilBean.criarMensagemDeAviso("Data Fim está Vazia");
        } else {
            minhasDoacoes = doacaoRN.pesquisarEntreDatas(dataInicio, dataFim);
        }        
        
    }
    
    private void buscarColaborador() {
        colaborador = UtilBean.obterContaLogada();
    }
    
    public List<Doacao> buscaMinhasDoacoes(){
        return doacaoRN.obterDoacoesPorColaborador(colaborador);
    }

    public List<Doacao> getMinhasDoacoes() {
        return minhasDoacoes;
    }

    public void setMinhasDoacoes(List<Doacao> minhasDoacoes) {
        this.minhasDoacoes = minhasDoacoes;
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
    
    
            
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.bean;

import br.org.centrocac.entidade.Campanha;
import br.org.centrocac.entidade.Doacao;
import br.org.centrocac.entidade.ItemDoacao;
import br.org.centrocac.rn.CampanhaRN;
import br.org.centrocac.rn.DoacaoRN;
import br.org.centrocac.util.UtilBean;
import com.mysql.cj.util.Util;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ANDRE
 */
@ManagedBean
@ViewScoped
public class ItemDoacaoBean {
    
    private String idDoacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");;
    private ItemDoacao itemDoacao = new ItemDoacao();
    
    //listas
    private List<Campanha> campanhas = new ArrayList();
    private List<ItemDoacao> itensDoacao = new ArrayList();
    
    //entidades
    private Doacao doacao = new Doacao();
    
    //RN
    private DoacaoRN doacaoRN = new DoacaoRN();
    private CampanhaRN campanhaRN = new CampanhaRN();
    
    @PostConstruct
    public void init(){
        doacao = doacaoRN.obter(Integer.parseInt(idDoacao));
        campanhas = campanhaRN.obterTodos();
        itemDoacao.setDoacao(doacao);
        System.out.println("oi 1");
        System.out.println(doacao.getId().toString());
    }
    
    public void salvarDoacao(){
        System.out.println("oi2");
        System.out.println(doacao.getItemDoacaoList());
        
        
        
        itensDoacao = doacao.getItemDoacaoList();
        itensDoacao.add(itemDoacao);
        
        doacao.setItemDoacaoList(itensDoacao);
        if(doacaoRN.salvar(doacao)){
            UtilBean.criarMensagemDeInformacao("Doação salva com sucesso!");
            this.goToDoacao();
        } else {
            UtilBean.criarMensagemDeErro("erro ao salvar");
        }
    }
    
    public String goToDoacao(){
        return "doacao.xhtml??faces-redirect=true";
    }

    public String getIdDoacao() {
        return idDoacao;
    }

    public void setIdDoacao(String idDoacao) {
        this.idDoacao = idDoacao;
    }

    public ItemDoacao getItemDoacao() {
        return itemDoacao;
    }

    public void setItemDoacao(ItemDoacao itemDoacao) {
        this.itemDoacao = itemDoacao;
    }

    public List<Campanha> getCampanhas() {
        return campanhas;
    }

    public void setCampanhas(List<Campanha> campanhas) {
        this.campanhas = campanhas;
    }
    
    
    
}

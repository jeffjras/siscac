/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.bean;

import br.org.centrocac.entidade.Campanha;
import br.org.centrocac.entidade.ItemCampanha;
import br.org.centrocac.rn.CampanhaRN;
import br.org.centrocac.util.UtilBean;
import java.io.IOException;
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
public class AddItemCampanhaBean {
    private String idCampanha = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
    
    //atributos
    private Campanha campanha = new Campanha();
    private ItemCampanha itemCampanha = new ItemCampanha();
    
    //RN
    private CampanhaRN campanhaRN = new CampanhaRN();
    
    //List 
    private List<ItemCampanha> itensCampanha = new ArrayList();
    
    @PostConstruct
    public void init(){
        this.buscarCampanha();
        itemCampanha.setCampanha(campanha);
    }
    
    private void buscarCampanha(){
        campanha = campanhaRN.obter(Integer.parseInt(idCampanha));
    }
    
    public String salvarItemCampanha() throws IOException{
        itensCampanha = campanha.getItemCampanhaList();
        itensCampanha.add(itemCampanha);
        campanha.setItemCampanhaList(itensCampanha);
        
        if(campanhaRN.salvar(campanha)){            
            UtilBean.criarMensagemDeInformacao("Item salvo com sucesso!");
            
        } else {
            UtilBean.criarMensagemDeAviso("Erro ao salvar!");
        }
        return "itemCampanha.xhtml?id="+campanha.getId()+"faces-redirect=true";
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public ItemCampanha getItemCampanha() {
        return itemCampanha;
    }

    public void setItemCampanha(ItemCampanha itemCampanha) {
        this.itemCampanha = itemCampanha;
    }
    
    
    
}

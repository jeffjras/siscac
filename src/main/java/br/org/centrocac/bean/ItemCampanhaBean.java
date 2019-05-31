/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.bean;

import br.org.centrocac.entidade.Campanha;
import br.org.centrocac.entidade.ItemCampanha;
import br.org.centrocac.rn.CampanhaRN;
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
public class ItemCampanhaBean {
    
    //atributos
    private String idCampanha = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
    
    //entidade
    private Campanha campanha = new Campanha();
    
    //RN
    private CampanhaRN campanhaRN = new CampanhaRN();
    
    //List
    private List<ItemCampanha> itensCampanha = new ArrayList();
    
    @PostConstruct
    public void init(){
        System.out.println("init bean item campanha");
        this.obterCampanha();
        
    }
    
    public void obterCampanha(){
        campanha = campanhaRN.obter(Integer.parseInt(idCampanha));
        
    }
    
    public List<ItemCampanha> listarTodos(){
        itensCampanha = campanha.getItemCampanhaList();
        return itensCampanha;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }
    
    
}
    
    
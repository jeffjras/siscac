/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.bean.converter;

import br.org.centrocac.entidade.Acao;
import br.org.centrocac.entidade.Campanha;
import br.org.centrocac.rn.CampanhaRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author toshiaki
 */
@FacesConverter(value = "convCampanha", forClass = Campanha.class)
public class CampanhaConverter implements Converter {

    CampanhaRN service = new CampanhaRN();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.trim().isEmpty() || "null".equalsIgnoreCase(string)) {
            System.out.println("teste1");
            return null;
        }
        try {
            System.out.println("teste2");
           //System.out.println(string);
            //System.out.println(service.obter(Integer.valueOf(string)).getNome());
            return service.obter(Integer.valueOf(string));
        } catch (Exception e) {
            System.out.println("teste3");
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null || !(o instanceof Campanha)) {
            return null;
        }
        Campanha Campanha = (Campanha) o;
        if (Campanha.getId() == null) {
            return null;
        }
        return String.valueOf(Campanha.getId());
    }

}

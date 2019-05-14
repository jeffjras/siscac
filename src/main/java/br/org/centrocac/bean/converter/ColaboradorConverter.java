/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.bean.converter;

import br.org.centrocac.entidade.Colaborador;
import br.org.centrocac.rn.ColaboradorRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author toshiaki
 */
@FacesConverter(value = "convColaborador", forClass = Colaborador.class)
public class ColaboradorConverter implements Converter {

    ColaboradorRN service = new ColaboradorRN();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.trim().isEmpty() || "null".equalsIgnoreCase(string)) {
            return null;
        }
        try {
            return service.obter(Integer.valueOf(string));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null || !(o instanceof Colaborador)) {
            return null;
        }
        Colaborador Colaborador = (Colaborador) o;
        if (Colaborador.getId() == null) {
            return null;
        }
        return String.valueOf(Colaborador.getId());
    }

}

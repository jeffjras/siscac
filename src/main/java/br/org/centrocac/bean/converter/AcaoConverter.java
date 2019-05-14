/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.bean.converter;

import br.org.centrocac.entidade.Acao;
import br.org.centrocac.rn.AcaoRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author toshiaki
 */
@FacesConverter(value = "convAcao", forClass = Acao.class)
public class AcaoConverter implements Converter {

    AcaoRN service = new AcaoRN();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.trim().isEmpty() || "null".equalsIgnoreCase(string)) {
            System.out.println("teste1");
            return null;
        }
        try {
            System.out.println("teste2");
            return service.obter(Integer.valueOf(string));
        } catch (Exception e) {
            System.out.println("teste3");
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null || !(o instanceof Acao)) {
            return null;
        }
        Acao Acao = (Acao) o;
        if (Acao.getId() == null) {
            return null;
        }
        return String.valueOf(Acao.getId());
    }

}

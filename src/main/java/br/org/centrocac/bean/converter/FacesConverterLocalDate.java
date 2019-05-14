/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.bean.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.convert.Converter;

/**
 *
 * @author toshiaki
 */
@FacesConverter(value = "dateConversor")
public class FacesConverterLocalDate implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date data = formato.parse(value);
            return data;
        } catch (ParseException ex) {
            Logger.getLogger(FacesConverterLocalDate.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Date dateValue = (Date) value;
        SimpleDateFormat formato = new SimpleDateFormat();
        return formato.format(value);

    }

}

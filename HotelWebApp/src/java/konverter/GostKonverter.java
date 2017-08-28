/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konverter;

import domen.Gost;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import sb.gost.SBgostLocal;

/**
 *
 * @author Sanja
 */
//@FacesConverter(value = "gostKNV")
@ManagedBean (name = "gostKNV")
@RequestScoped
public class GostKonverter implements Converter {
    
    @EJB
    SBgostLocal sbGost;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        if (value!=null && !value.isEmpty()){
            
            int id = Integer.parseInt(value);
            Gost g = sbGost.vratiGosta(id);
            System.out.println("drzavljanstvo konverter:"+g.getIme());
            return g;
            
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        if (value!=null && (value instanceof Gost)){
            
            Gost g = (Gost) value;
            return g.getGostID().toString();
        }
        return "";
    }

    
    
}

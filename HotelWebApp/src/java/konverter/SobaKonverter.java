/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konverter;

import domen.Soba;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import sb.soba.SBsobaLocal;

/**
 *
 * @author Sanja
 */
//@FacesConverter(value = "sobaKNV")
@ManagedBean (name = "sobaKNV")
@RequestScoped
public class SobaKonverter implements Converter{

    @EJB
    SBsobaLocal sbSoba;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value!=null && !value.isEmpty()){
            
            int id = Integer.parseInt(value);
            Soba s = sbSoba.vratiSobu(id);
            System.out.println("drzavljanstvo konverter:"+s.getBrojSobe());
            return s;
            
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value!=null && (value instanceof Soba)){
            
           Soba s = (Soba) value;
            return s.getSobaID().toString();
        }
        return "";
    }
    
}

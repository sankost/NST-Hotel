/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konverter;

import domen.Drzavljanstvo;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import sb.drzavljanstvo.SBdrzavljanstvoLocal;

/**
 *
 * @author Sanja
 */

//@FacesConverter(value = "drzavljanstvoKNV")
@ManagedBean (name = "drzavljanstvoKNV")
@RequestScoped
public class DrzavljanstvoKonverter implements Converter {
    
    @EJB
    SBdrzavljanstvoLocal sbDrzavljanstvo;
    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        if (value!=null && !value.isEmpty()){            
            int id = Integer.parseInt(value);
            Drzavljanstvo d = sbDrzavljanstvo.vratiDrzavljanstvoID(id);
            System.out.println("drzavljanstvo konverter:"+d.getNaziv());
            return d;
            
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        if (value!=null && (value instanceof Drzavljanstvo)){
            
            Drzavljanstvo d = (Drzavljanstvo) value;
            return d.getDrzavljanstvoID().toString();
        }
        return "";
    }
}

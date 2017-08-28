/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Sanja
 */
@FacesValidator(value = "jmbgValidator")
public class JMBGvalidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value!=null){
                
                String jmbgStr = value.toString();
                
                if(jmbgStr.length() != 13){
                     throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "JMBG mora sadrzati 13 cifara.", "JMBG mora sadrzati 13 cifara."));
                }
                
                for (int i = 0; i < jmbgStr.length(); i++) {
                    if (!Character.isDigit(jmbgStr.charAt(i))){
                        
                        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "JMBG mora sadrzati samo brojeve.", "JMBG mora sadrzati samo brojeve."));
                        
                    }
                }
                
                
            }
    }
    
}

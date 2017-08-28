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
@FacesValidator(value = "tekstValidator")
public class TekstValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value!=null){
                
                String tekstStr = value.toString();
                
                
                for (int i = 0; i < tekstStr.length(); i++) {
                    if (Character.isDigit(tekstStr.charAt(i))){
                        
                        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska u validaciji teksta", ""));
                        
                    }
                }  
            }
    }
    
}

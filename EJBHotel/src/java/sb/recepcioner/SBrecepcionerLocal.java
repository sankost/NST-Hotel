/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.recepcioner;

import domen.Recepcioner;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Sanja
 */
@Local
public interface SBrecepcionerLocal {
    Recepcioner vratiRecepcionera(Integer recepcionerID);
    List<Recepcioner> vratiListuRecepcionera();


}

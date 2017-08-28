/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.soba;

import domen.Soba;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Sanja
 */
@Local
public interface SBsobaLocal {
    Soba vratiSobu(Integer sobaID);
    
    List<Soba> vratiListuSoba();
    
}

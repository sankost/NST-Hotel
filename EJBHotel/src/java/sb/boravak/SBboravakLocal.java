/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.boravak;

import domen.Boravak;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Sanja
 */
@Local
public interface SBboravakLocal {
    
    Boravak vratiBoravak(Integer boravakID);
    
    List<Boravak> vratiListuBoravaka();
    
    void sacuvajBoravak(Boravak boravak);
    
    void obrisiBoravak(Boravak boravak);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.gost;

import domen.Gost;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Sanja
 */
@Local
public interface SBgostLocal {

    Gost vratiGosta(Integer gostID);
    
    List<Gost> vratiListuGostiju();
    
    void sacuvajGosta(Gost gost);
    
    void obrisiGosta(Gost gost);
    
}

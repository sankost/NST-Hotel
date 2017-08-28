/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.uplata;

import domen.Uplata;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Sanja
 */
@Local
public interface SBuplataLocal {
    Uplata vratiUplatu (int uplataID);
    List<Uplata> vratiListuUplata();
    void sacuvajUplatu(Uplata uplata);
    void obrisiUplatu(Uplata uplata);
}

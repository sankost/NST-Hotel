/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.tipSobe;


import domen.Tipsobe;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Sanja
 */
@Local
public interface SBtipSobeLocal {
    List<Tipsobe> vratiListuTipovaSoba();
    public Tipsobe vratiTipSobeID(Integer tipID);
}

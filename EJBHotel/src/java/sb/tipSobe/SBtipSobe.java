/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.tipSobe;

import domen.Tipsobe;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sanja
 */
@Stateless
public class SBtipSobe implements SBtipSobeLocal {
    @PersistenceContext(unitName = "EJBHotelPU")
    private EntityManager em;

    @Override
    public List<Tipsobe> vratiListuTipovaSoba() {
         return em.createNamedQuery("Tipsobe.findAll").getResultList(); 
    }

    @Override
    public Tipsobe vratiTipSobeID(Integer tipID) {
        return em.find(Tipsobe.class, tipID); 
    }

}

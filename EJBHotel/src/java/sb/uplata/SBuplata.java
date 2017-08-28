/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.uplata;

import domen.Uplata;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sanja
 */
@Stateless
public class SBuplata implements SBuplataLocal {

    @PersistenceContext(unitName = "EJBHotelPU")
    private EntityManager em;

    @Override
    public Uplata vratiUplatu(int uplataID) {
        return em.find(Uplata.class, uplataID);
    }

    @Override
    public List<Uplata> vratiListuUplata() {
        return em.createNamedQuery("Uplata.findAll").getResultList();
    }

    @Override
    public void sacuvajUplatu(Uplata uplata) {
        em.merge(uplata);
    }
    
    @Override
    public void obrisiUplatu(Uplata uplata){
        em.remove(em.merge(uplata));
    }
    
}

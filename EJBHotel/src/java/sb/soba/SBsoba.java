/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.soba;

import domen.Soba;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sanja
 */
@Stateless
public class SBsoba implements SBsobaLocal {

    @PersistenceContext(unitName = "EJBHotelPU")
    private EntityManager em;
    
    @Override
    public Soba vratiSobu(Integer sobaID) {
        return em.find(Soba.class, sobaID);
    }

    @Override
    public List<Soba> vratiListuSoba() {
        return em.createNamedQuery("Soba.findAll").getResultList();
    }


}

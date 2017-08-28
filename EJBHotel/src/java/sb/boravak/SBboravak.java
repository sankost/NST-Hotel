/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.boravak;

import domen.Boravak;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sanja
 */
@Stateless
public class SBboravak implements SBboravakLocal {

    @PersistenceContext(unitName = "EJBHotelPU")
    private EntityManager em;
    
    @Override
    public Boravak vratiBoravak(Integer boravakID) {
        return em.find(Boravak.class, boravakID);
    }

    @Override
    public List<Boravak> vratiListuBoravaka() {
        return em.createNamedQuery("Boravak.findAll").getResultList();
    }

    @Override
    public void sacuvajBoravak(Boravak boravak) {
        em.merge(boravak);
    }

    @Override
    public void obrisiBoravak(Boravak boravak) {
        em.remove(em.merge(boravak));
    }

    
}

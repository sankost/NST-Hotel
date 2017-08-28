/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sb.gost;

//import domen.Drzavljanstvo;
import domen.Gost;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sanja
 */
@Stateless
public class SBgost implements SBgostLocal {
    @PersistenceContext(unitName = "EJBHotelPU")
    private EntityManager em;

    @Override
    public Gost vratiGosta(Integer gostID) {
        Gost db_g = em.find(Gost.class, gostID);
        return db_g;
    }

    @Override
    public List<Gost> vratiListuGostiju() {
        return em.createNamedQuery("Gost.findAll").getResultList();
    }

    @Override
    public void sacuvajGosta(Gost gost) {
        em.merge(gost);
    }

    @Override
    public void obrisiGosta(Gost gost){
         em.remove(em.merge(gost));

    }
    
    
}

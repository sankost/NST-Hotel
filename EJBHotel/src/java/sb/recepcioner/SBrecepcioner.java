/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.recepcioner;
import domen.Recepcioner;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sanja
 */
@Stateless
public class SBrecepcioner implements SBrecepcionerLocal {
    @PersistenceContext(unitName = "EJBHotelPU")
    private EntityManager em;
    
    @Override
    public Recepcioner vratiRecepcionera(Integer recepcionerID) {
        return em.find(Recepcioner.class, recepcionerID);     
    }

    @Override
    public List<Recepcioner> vratiListuRecepcionera() {
        return em.createNamedQuery("Recepcioner.findAll").getResultList();
    }
    
}

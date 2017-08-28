/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.drzavljanstvo;

import domen.Drzavljanstvo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sanja
 */
@Stateless
public class SBdrzavljanstvo implements SBdrzavljanstvoLocal {
    @PersistenceContext(unitName = "EJBHotelPU")
    private EntityManager em;


    @Override
    public List<Drzavljanstvo> vratiListuDrzavljanstava() {
        List<Drzavljanstvo> lista = em.createNamedQuery("Drzavljanstvo.findAll").getResultList();
        for (Drzavljanstvo d : lista) {
            int x = d.getGostList().size();
            System.out.println("Drzavljanstvo: "+d.getNaziv()+" ima ukupno "+x+" gostiju.");
        }
        return lista;
    }

    @Override
    public Drzavljanstvo vratiDrzavljanstvoID(Integer drzavljanstvoID) {
        Drzavljanstvo d = em.find(Drzavljanstvo.class, drzavljanstvoID);        
        return d;
    }

  
    
}

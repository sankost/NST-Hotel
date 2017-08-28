/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import domen.Drzavljanstvo;
import domen.Gost;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sb.drzavljanstvo.SBdrzavljanstvoLocal;
import sb.gost.SBgostLocal;

/**
 *
 * @author Sanja
 */
@ManagedBean
@ViewScoped
public class MbPretragaGostiju implements Serializable{

    private List<Drzavljanstvo> listaDrzavljanstava;
    private List<Gost> listaGostiju;
    private Gost gost;

    @EJB
    private SBdrzavljanstvoLocal sbDrzavljanstvo;

    @EJB
    private SBgostLocal sbGost;
    
    
    MbObradaGosta mbObradaGosta;


    public MbPretragaGostiju() {
        System.out.println("Konstruktor MbPretragaGostiju()");
        gost = new Gost();
    }

    @PostConstruct
    public void inicijalizujPodatke() {
        listaDrzavljanstava = sbDrzavljanstvo.vratiListuDrzavljanstava();
        listaGostiju = sbGost.vratiListuGostiju();
    }

    public List<Drzavljanstvo> getListaDrzavljanstava() {
        return listaDrzavljanstava;
    }

    public void setListaDrzavljanstava(List<Drzavljanstvo> listaDrzavljanstava) {
        this.listaDrzavljanstava = listaDrzavljanstava;
    }

    public List<Gost> getListaGostiju() {
        return listaGostiju;
    }

    public void setListaGostiju(List<Gost> listaGostiju) {
        this.listaGostiju = listaGostiju;
    }

    public Gost getGost() {
        return gost;
    }

    public void setGost(Gost gost) {
        this.gost = gost;
    }

}

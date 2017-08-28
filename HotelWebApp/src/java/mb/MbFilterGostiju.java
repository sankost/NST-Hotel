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
public class MbFilterGostiju implements Serializable{

    private List<Gost> listaGostiju;
    private List<Gost> listaFiltriranihGostiju;

    @EJB
    private SBgostLocal sbGost;
    
    @EJB
    private SBdrzavljanstvoLocal sbDrzavljanstvo;
    
    
    @PostConstruct
    public void init() {
        listaGostiju = sbGost.vratiListuGostiju();
    }

    public List<Gost> getListaGostiju() {
        return listaGostiju;
    }

    public List<Gost> getListaFiltriranihGostiju() {
        return listaFiltriranihGostiju;
    }

    public void setListaFiltriranihGostiju(List<Gost> listaFiltriranihGostiju) {
        this.listaFiltriranihGostiju = listaFiltriranihGostiju;
    }

    
    public List<Drzavljanstvo> vrtiListuDrzavljanstava(){
        return sbDrzavljanstvo.vratiListuDrzavljanstava();
    }
}

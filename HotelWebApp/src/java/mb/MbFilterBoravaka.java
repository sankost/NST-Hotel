/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import domen.Boravak;
import domen.Gost;
import domen.Soba;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sb.boravak.SBboravakLocal;
import sb.gost.SBgostLocal;
import sb.soba.SBsobaLocal;

/**
 *
 * @author Sanja
 */
@ManagedBean
@ViewScoped
public class MbFilterBoravaka implements Serializable{

    private List<Boravak> listaBoravaka;
    private List<Boravak> listaFiltriranihBoravaka;
    
    @EJB
    private SBgostLocal sbGost;
    
    @EJB
    private SBsobaLocal sbSoba;
    
    @EJB
    private SBboravakLocal sbBoravak;
    
    public MbFilterBoravaka() {
    }
    
    @PostConstruct
    public void init() {
        listaBoravaka = sbBoravak.vratiListuBoravaka();
    }

    public List<Boravak> getListaFiltriranihBoravaka() {
        return listaFiltriranihBoravaka;
    }

    public void setListaFiltriranihBoravaka(List<Boravak> listaFiltriranihBoravaka) {
        this.listaFiltriranihBoravaka = listaFiltriranihBoravaka;
    }
    
    public List<Soba> vratiListuSoba(){
        return sbSoba.vratiListuSoba();
    }
    
    public List<Gost> vratiLisyuGostiju(){
        return sbGost.vratiListuGostiju();
    }
}

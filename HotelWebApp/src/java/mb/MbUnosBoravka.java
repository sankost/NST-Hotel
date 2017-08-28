/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import domen.Boravak;
import domen.Gost;
import domen.Soba;
import domen.Uplata;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import sb.boravak.SBboravakLocal;
import sb.gost.SBgostLocal;
import sb.soba.SBsobaLocal;
import sb.uplata.SBuplataLocal;

/**
 *
 * @author Sanja
 */
@ManagedBean
@ViewScoped
public class MbUnosBoravka implements Serializable{

    private List<Gost> listaGostiju;
    private List<Soba> listaSoba;
    private Boravak boravak;
    private Uplata uplata;

    @EJB
    private SBgostLocal sbGost;

    @EJB
    private SBsobaLocal sbSoba;

    @EJB
    private SBboravakLocal sbBoravak;

    @EJB
    private SBuplataLocal sbUplata;

    @ManagedProperty("#{mbObradaRecepcionera}")
    private MbObradaRecepcionera mbObradaRecepcionera;

    private boolean izmena;
    private boolean vidljivost;
    
     public MbUnosBoravka() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        boravak = (Boravak) httpSession.getAttribute("boravak");
        if (boravak != null) {
            //izmena = true;
            vidljivost = true;
            httpSession.setAttribute("boravak", null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Podaci o izabranom boravku su uspešno pronađeni.", ""));
        } else {
            boravak = new Boravak();
        }
        
    }
    
    @PostConstruct
    public void inicijalizujPodatke() {
        listaGostiju = sbGost.vratiListuGostiju();
        listaSoba = sbSoba.vratiListuSoba();
    }
     
    public List<Gost> getListaGostiju() {
        return listaGostiju;
    }

    public void setListaGostiju(List<Gost> listaGostiju) {
        this.listaGostiju = listaGostiju;
    }

    public List<Soba> getListaSoba() {
        return listaSoba;
    }

    public void setListaSoba(List<Soba> listaSoba) {
        this.listaSoba = listaSoba;
    }
    
     public Boravak getBoravak() {
        return boravak;
    }

    public boolean isIzmena() {
        return izmena;
    }

    public void setIzmena(boolean izmena) {
        this.izmena = izmena;
    }
    
     
    public void setBoravak(Boravak boravak) {
            this.boravak = boravak;
 
    }

    public void setMbObradaRecepcionera(MbObradaRecepcionera mbObradaRecepcionera) {
        this.mbObradaRecepcionera = mbObradaRecepcionera;
    }
    
    public Uplata getUplata() {
        return uplata;
    }

    public void setUplata(Uplata uplata) {
        this.uplata = uplata;
    }

    public boolean isVidljivost() {
        return vidljivost;
    }

    public void setVidljivost(boolean vidljivost) {
        this.vidljivost = vidljivost;
    }
    
    
    public void sacuvajBoravak() {

        System.out.println(boravak.toString());
        boravak.setRecepcionerID(mbObradaRecepcionera.rec);
        if (boravak.getDatumOd().after(boravak.getDatumDo())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sistem ne može da zapamti podatke o boravku!", "Sistem ne može da zapamti nov boravak!"));
            return;
        }

        sbBoravak.sacuvajBoravak(boravak);
        izmena = false;
        boravak = new Boravak();

        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "pretragaBoravaka.xhtml");

    }
    
    public void pokreniIzmenu(){
        izmena = true;
        vidljivost = false;
       // return "unosBoravka";
    }

    public String vratiVidljivost() {
        if (vidljivost == true) {
            return "visible";
        }
        return "hidden";
    }
    
    public String vratiVidljivostSave() {
        if (vidljivost == false) {
            return "visible";
        }
        return "hidden";
    }
}

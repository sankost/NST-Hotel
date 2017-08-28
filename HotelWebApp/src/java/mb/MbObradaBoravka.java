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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
public class MbObradaBoravka implements Serializable {

    private List<Gost> listaGostiju;
    private List<Soba> listaSoba;
    private List<Boravak> listaBoravaka;
    private List<Uplata> listaUplata;
    private List<Uplata> listaUplataZaBoravak;
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
    
     @ManagedProperty("#{mbPretragaBoravaka}")
    private MbUnosBoravka mbPretragaBoravaka;


    public MbObradaBoravka() {
        boravak = null;
        uplata = new Uplata();
    }

    @PostConstruct
    public void inicijalizujPodatke() {
        listaGostiju = sbGost.vratiListuGostiju();
        listaSoba = sbSoba.vratiListuSoba();
        listaBoravaka = sbBoravak.vratiListuBoravaka();
        listaUplata = sbUplata.vratiListuUplata();
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

    public List<Boravak> getListaBoravaka() {
        return listaBoravaka;
    }

    public void setListaBoravaka(List<Boravak> listaBoravaka) {
        this.listaBoravaka = listaBoravaka;
    }

    public Boravak getBoravak() {
        return boravak;
    }

    public Uplata getUplata() {
        return uplata;
    }

    public void setUplata(Uplata uplata) {
        this.uplata = uplata;
    }
    

    public void setBoravak(Boravak boravak) {
        this.boravak = boravak;
        listaUplataZaBoravak = new ArrayList<>();
        for (Uplata u : sbUplata.vratiListuUplata()) {
            if (Objects.equals(u.getBoravakID(), boravak)) {
                listaUplataZaBoravak.add(u);
                System.out.println(u.toString());
            }
        }

    }

    public List<Uplata> getListaUplata() {
        return listaUplata;
    }

    public void setListaUplata(List<Uplata> listaUplata) {
        this.listaUplata = listaUplata;
    }

    public void osveziListu() {
        listaBoravaka = sbBoravak.vratiListuBoravaka();
    }

    public void setMbObradaRecepcionera(MbObradaRecepcionera mbObradaRecepcionera) {
        this.mbObradaRecepcionera = mbObradaRecepcionera;
    }

    public List<Uplata> getListaUplataZaBoravak() {
        return listaUplataZaBoravak;
    }

    public void setListaUplataZaBoravak(List<Uplata> listaUplataZaBoravak) {
        this.listaUplataZaBoravak = listaUplataZaBoravak;
    }

    public MbUnosBoravka getMbPretragaBoravaka() {
        return mbPretragaBoravaka;
    }

    public void setMbPretragaBoravaka(MbUnosBoravka mbPretragaBoravaka) {
        this.mbPretragaBoravaka = mbPretragaBoravaka;
    }

    public String pokreniIzmenu() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        httpSession.setAttribute("boravak", boravak);
        return "unosBoravka";

    }

    public void obrisiBoravak() {
        try {
            Boravak bor = sbBoravak.vratiBoravak(boravak.getBoravakID());
            if(bor==null){
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sistem ne može da obriše boravak.", "Boravak ne moze biti obrisan"));
                 return;
            }
            for (Uplata u : sbUplata.vratiListuUplata()) {
                if (Objects.equals(u.getBoravakID(), boravak)) {
                    sbUplata.obrisiUplatu(u);
                }
            }
            sbBoravak.obrisiBoravak(boravak);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska!", "Boravak ne moze biti obrisan"));
            return;
        }
        System.out.println(boravak.toString());
        osveziListu();
        boravak = new Boravak();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sistem je obrisao boravak", "Boravak je uspesno obrisan."));
        
    }
    
    public void sacuvajUplatu(){
        int iznos = 0;
        for(Uplata u : listaUplataZaBoravak){
            iznos += u.getIznos();
        }
        if(uplata.getIznos() > boravak.getCena()-iznos){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sistem ne može da zapamti novu uplatu.", ""));
            return;
        }
        uplata.setBoravakID(boravak);
        sbUplata.sacuvajUplatu(uplata);
        System.out.println("Cuvanje uplate"+uplata.toString());
        uplata = new Uplata();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistem je zapamtio novu uplatu.", ""));
            
    }
    
    public String prikaziTabelu(){
        return boravak!=null ? "visible" : "hidden";
       
    }
}

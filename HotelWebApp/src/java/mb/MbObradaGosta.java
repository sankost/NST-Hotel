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
//import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
//import sb.gost.SBgostLocal;
import webservices.DrzavljanstvoService;
import webservices.GostService;

/**
 *
 * @author Sanja
 */
@ManagedBean
@RequestScoped
public class MbObradaGosta implements Serializable {

    private List<Drzavljanstvo> listaDrzavljanstava;
    private List<Gost> listaGostiju;
    private Gost gost;

//    @EJB
//    private SBdrzavljanstvoLocal sbDrzavljanstvo;
//    @EJB
//    private SBgostLocal sbGost;

    private boolean izmena;
    private boolean vidljivost;

    public MbObradaGosta() {
        gost = new Gost();
    }

    @PostConstruct
    public void inicijalizujPodatke() {
//        listaDrzavljanstava = sbDrzavljanstvo.vratiListuDrzavljanstava();
//        listaGostiju = sbGost.vratiListuGostiju();
        listaDrzavljanstava = vratiDrzavljanstva();
        listaGostiju = vratiGoste();
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
        Gost gost1 = null;
        try {
//            gost1 = sbGost.vratiGosta(gost.getGostID());
            gost1 = vratiGostaWebService(gost.getGostID());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sistem ne može da pronađe podatke o gostu, gost nije izabran", ""));
            return;
        }
        if (gost1 == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sistem ne može da pronađe podatke o gostu", ""));
        } else {
            this.gost = gost;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Podaci o izabranom gostu su uspešno pronađeni.", ""));
        }
    }

    private void osveziListu() {
        listaGostiju = vratiGoste();
    }

    public boolean isIzmena() {
        return izmena;
    }

    public void setIzmena(boolean izmena) {
        this.izmena = izmena;
    }

    public void setVidljivost(boolean vidljivost) {
        this.vidljivost = vidljivost;
    }

    public boolean isVidljivost() {
        return vidljivost;
    }

    public void sacuvajGosta() {
        System.out.println("sacuvajGosta " + gost.getGostID());
        for (Gost g : listaGostiju) {
            if (g.getJmbg().equals(gost.getJmbg()) && !g.equals(gost)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sistem ne može da zapamti podatke o gostu. Greška pri unosu podataka!", "Gost ne moze biti sacuvan. Dva gosta ne mogu imati isti JMBG!"));
                return;
            }
        }
//        sbGost.sacuvajGosta(gost);
        sacuvajGostaWebService(gost);

        izmena = false;
        osveziListu();
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "pretragaGostiju.xhtml");

    }

    public String pokreniIzmenu() {
        vidljivost = true;
        return "unosGosta";

    }

    public void izmeni() {
        izmena = true;
        vidljivost = false;
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

    public void obrisiGosta() {
        try {
//            sbGost.obrisiGosta(gost);
            obrisiGostaWebService(gost);
            osveziListu();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sistem ne moze da obrise gosta!", "Gost ne moze biti obrisan"));
            return;
        }
        gost = new Gost();
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "pretragaGostiju.xhtml");
    }

    private <T> T vratiDrzavljanstva() {
        GenericType<List<Drzavljanstvo>> genTipLista = new GenericType<List<Drzavljanstvo>>() {
        };
        DrzavljanstvoService client = new DrzavljanstvoService();
        T result = (T) client.findAll_JSON(genTipLista);
        client.close();
        return result;
    }

    private void sacuvajGostaWebService(Object gost) {
        GostService clientGost = new GostService();
        clientGost.create_JSON(gost);
        clientGost.close();
    }

    private void obrisiGostaWebService(Gost gost) {
        GostService clientGost = new GostService();
        try {
            clientGost.remove(gost.getGostID().toString());
        } catch (ClientErrorException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sistem ne moze da obrise gosta!", "Gost ne moze biti obrisan"));
            return;
        }
        clientGost.close();
    }

    private <T> T vratiGoste() {
        GenericType<List<Gost>> genTipLista = new GenericType<List<Gost>>() {
        };
        GostService client = new GostService();
        T result = (T) client.findAll_JSON(genTipLista);
        client.close();
        return result;
    }

    private Gost vratiGostaWebService(Integer gostID) {
        GostService client = new GostService();
        Gost result = (Gost) client.find_JSON(Gost.class, gostID.toString());
        client.close();
        return result;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import javax.faces.context.FacesContext;
import domen.Recepcioner;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sb.recepcioner.SBrecepcionerLocal;

/**
 *
 * @author Sanja
 */
@ManagedBean
@SessionScoped
public class MbObradaRecepcionera implements Serializable {

    @EJB
    SBrecepcionerLocal sbRec;

    Recepcioner rec;
    private String username;
    private String password;
    private List<Recepcioner> listaRecepcionera;

    public String getUsername() {
        return username;
    }

    @PostConstruct
    public void inicijalizujPodatke() {
        listaRecepcionera = sbRec.vratiListuRecepcionera();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MbObradaRecepcionera() {
        rec = new Recepcioner();
    }

    public void setRec(Recepcioner rec) {
        this.rec = rec;
    }

    public Recepcioner getRec() {
        return rec;
    }

    public String vratiRecepcionera() {
        String page = "";
        try {
            for (Recepcioner r : listaRecepcionera) {
                if (r.getKorisnickoIme().equals(username) && r.getKorisnickaSifra().equals(password)) {
                    rec = sbRec.vratiRecepcionera(r.getRecepcionerID());
                }
                page = "index";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška!", "Sistem ne može da pronađe recepcionera"));
            page = "";
        }
        return page;
    }

}

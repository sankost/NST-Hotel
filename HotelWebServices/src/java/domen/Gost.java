/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sanja
 */
@Entity
@Table(name = "gost")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gost.findAll", query = "SELECT g FROM Gost g")
    , @NamedQuery(name = "Gost.findByGostID", query = "SELECT g FROM Gost g WHERE g.gostID = :gostID")
    , @NamedQuery(name = "Gost.findByIme", query = "SELECT g FROM Gost g WHERE g.ime = :ime")
    , @NamedQuery(name = "Gost.findByPrezime", query = "SELECT g FROM Gost g WHERE g.prezime = :prezime")
    , @NamedQuery(name = "Gost.findByJmbg", query = "SELECT g FROM Gost g WHERE g.jmbg = :jmbg")
    , @NamedQuery(name = "Gost.findByKontakt", query = "SELECT g FROM Gost g WHERE g.kontakt = :kontakt")
    , @NamedQuery(name = "Gost.findByMail", query = "SELECT g FROM Gost g WHERE g.mail = :mail")})
public class Gost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gostID")
    private Integer gostID;
    @Size(max = 10)
    @Column(name = "ime")
    private String ime;
    @Size(max = 20)
    @Column(name = "prezime")
    private String prezime;
    @Size(max = 13)
    @Column(name = "JMBG")
    private String jmbg;
    @Size(max = 15)
    @Column(name = "kontakt")
    private String kontakt;
    @Size(max = 25)
    @Column(name = "mail")
    private String mail;
    @JoinColumn(name = "drzavljanstvoID", referencedColumnName = "drzavljanstvoID")
    @ManyToOne
    private Drzavljanstvo drzavljanstvoID;

    public Gost() {
    }

    public Gost(Integer gostID) {
        this.gostID = gostID;
    }

    public Integer getGostID() {
        return gostID;
    }

    public void setGostID(Integer gostID) {
        this.gostID = gostID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Drzavljanstvo getDrzavljanstvoID() {
        return drzavljanstvoID;
    }

    public void setDrzavljanstvoID(Drzavljanstvo drzavljanstvoID) {
        this.drzavljanstvoID = drzavljanstvoID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gostID != null ? gostID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gost)) {
            return false;
        }
        Gost other = (Gost) object;
        if ((this.gostID == null && other.gostID != null) || (this.gostID != null && !this.gostID.equals(other.gostID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Gost[ gostID=" + gostID + " ]";
    }
    
}

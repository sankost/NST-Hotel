/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sanja
 */
@Entity
@Table(name = "recepcioner")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recepcioner.findAll", query = "SELECT r FROM Recepcioner r"),
    @NamedQuery(name = "Recepcioner.findByRecepcionerID", query = "SELECT r FROM Recepcioner r WHERE r.recepcionerID = :recepcionerID"),
    @NamedQuery(name = "Recepcioner.findByIme", query = "SELECT r FROM Recepcioner r WHERE r.ime = :ime"),
    @NamedQuery(name = "Recepcioner.findByPrezime", query = "SELECT r FROM Recepcioner r WHERE r.prezime = :prezime"),
    @NamedQuery(name = "Recepcioner.findByKorisnickoIme", query = "SELECT r FROM Recepcioner r WHERE r.korisnickoIme = :korisnickoIme"),
    @NamedQuery(name = "Recepcioner.findByKorisnickaSifra", query = "SELECT r FROM Recepcioner r WHERE r.korisnickaSifra = :korisnickaSifra")})
public class Recepcioner implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "recepcionerID")
    private Integer recepcionerID;
    @Size(max = 10)
    @Column(name = "ime")
    private String ime;
    @Size(max = 20)
    @Column(name = "prezime")
    private String prezime;
    @Size(max = 15)
    @Column(name = "korisnickoIme")
    private String korisnickoIme;
    @Size(max = 15)
    @Column(name = "korisnickaSifra")
    private String korisnickaSifra;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "recepcionerID")
    private List<Boravak> boravakList;

    public Recepcioner() {
    }

    public Recepcioner(Integer recepcionerID) {
        this.recepcionerID = recepcionerID;
    }

    public Integer getRecepcionerID() {
        return recepcionerID;
    }

    public void setRecepcionerID(Integer recepcionerID) {
        this.recepcionerID = recepcionerID;
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getKorisnickaSifra() {
        return korisnickaSifra;
    }

    public void setKorisnickaSifra(String korisnickaSifra) {
        this.korisnickaSifra = korisnickaSifra;
    }

    @XmlTransient
    public List<Boravak> getBoravakList() {
        return boravakList;
    }

    public void setBoravakList(List<Boravak> boravakList) {
        this.boravakList = boravakList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recepcionerID != null ? recepcionerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recepcioner)) {
            return false;
        }
        Recepcioner other = (Recepcioner) object;
        if ((this.recepcionerID == null && other.recepcionerID != null) || (this.recepcionerID != null && !this.recepcionerID.equals(other.recepcionerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
    }
    
}

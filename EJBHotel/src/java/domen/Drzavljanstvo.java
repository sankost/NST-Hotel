/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name = "drzavljanstvo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Drzavljanstvo.findAll", query = "SELECT d FROM Drzavljanstvo d"),
    @NamedQuery(name = "Drzavljanstvo.findByDrzavljanstvoID", query = "SELECT d FROM Drzavljanstvo d WHERE d.drzavljanstvoID = :drzavljanstvoID"),
    @NamedQuery(name = "Drzavljanstvo.findByNaziv", query = "SELECT d FROM Drzavljanstvo d WHERE d.naziv = :naziv")})
public class Drzavljanstvo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "drzavljanstvoID")
    private Integer drzavljanstvoID;
    @Size(max = 20)
    @Column(name = "naziv")
    private String naziv;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "drzavljanstvoID")
    private List<Gost> gostList;

    public Drzavljanstvo() {
        gostList = new ArrayList<>();
    }

    public Drzavljanstvo(Integer drzavljanstvoID) {
        this.drzavljanstvoID = drzavljanstvoID;
    }

    public Integer getDrzavljanstvoID() {
        return drzavljanstvoID;
    }

    public void setDrzavljanstvoID(Integer drzavljanstvoID) {
        this.drzavljanstvoID = drzavljanstvoID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @XmlTransient
    public List<Gost> getGostList() {
        return gostList;
    }

    public void setGostList(List<Gost> gostList) {
        this.gostList = gostList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (drzavljanstvoID != null ? drzavljanstvoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Drzavljanstvo)) {
            return false;
        }
        Drzavljanstvo other = (Drzavljanstvo) object;
        if ((this.drzavljanstvoID == null && other.drzavljanstvoID != null) || (this.drzavljanstvoID != null && !this.drzavljanstvoID.equals(other.drzavljanstvoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return naziv;
    }
    
}

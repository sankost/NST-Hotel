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
@Table(name = "tipsobe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipsobe.findAll", query = "SELECT t FROM Tipsobe t"),
    @NamedQuery(name = "Tipsobe.findByTipID", query = "SELECT t FROM Tipsobe t WHERE t.tipID = :tipID"),
    @NamedQuery(name = "Tipsobe.findByNazivTipa", query = "SELECT t FROM Tipsobe t WHERE t.nazivTipa = :nazivTipa")})
public class Tipsobe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipID")
    private Integer tipID;
    @Size(max = 20)
    @Column(name = "nazivTipa")
    private String nazivTipa;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tipSobe")
    private List<Soba> sobaList;

    public Tipsobe() {
    }

    public Tipsobe(Integer tipID) {
        this.tipID = tipID;
    }

    public Integer getTipID() {
        return tipID;
    }

    public void setTipID(Integer tipID) {
        this.tipID = tipID;
    }

    public String getNazivTipa() {
        return nazivTipa;
    }

    public void setNazivTipa(String nazivTipa) {
        this.nazivTipa = nazivTipa;
    }

    @XmlTransient
    public List<Soba> getSobaList() {
        return sobaList;
    }

    public void setSobaList(List<Soba> sobaList) {
        this.sobaList = sobaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipID != null ? tipID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipsobe)) {
            return false;
        }
        Tipsobe other = (Tipsobe) object;
        if ((this.tipID == null && other.tipID != null) || (this.tipID != null && !this.tipID.equals(other.tipID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Tipsobe[ tipID=" + tipID + " ]";
    }
    
}

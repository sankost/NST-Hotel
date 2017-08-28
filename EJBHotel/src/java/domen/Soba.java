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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "soba")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Soba.findAll", query = "SELECT s FROM Soba s"),
    @NamedQuery(name = "Soba.findBySobaID", query = "SELECT s FROM Soba s WHERE s.sobaID = :sobaID"),
    @NamedQuery(name = "Soba.findByBrojSobe", query = "SELECT s FROM Soba s WHERE s.brojSobe = :brojSobe"),
    @NamedQuery(name = "Soba.findBySprat", query = "SELECT s FROM Soba s WHERE s.sprat = :sprat")})
public class Soba implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "sobaID")
    private Integer sobaID;
    @Size(max = 5)
    @Column(name = "brojSobe")
    private String brojSobe;
    @Size(max = 10)
    @Column(name = "sprat")
    private String sprat;
    @JoinColumn(name = "tipSobe", referencedColumnName = "tipID")
    @ManyToOne
    private Tipsobe tipSobe;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sobaID")
    private List<Boravak> boravakList;

    public Soba() {
    }

    public Soba(Integer sobaID) {
        this.sobaID = sobaID;
    }

    public Integer getSobaID() {
        return sobaID;
    }

    public void setSobaID(Integer sobaID) {
        this.sobaID = sobaID;
    }

    public String getBrojSobe() {
        return brojSobe;
    }

    public void setBrojSobe(String brojSobe) {
        this.brojSobe = brojSobe;
    }

    public String getSprat() {
        return sprat;
    }

    public void setSprat(String sprat) {
        this.sprat = sprat;
    }

    public Tipsobe getTipSobe() {
        return tipSobe;
    }

    public void setTipSobe(Tipsobe tipSobe) {
        this.tipSobe = tipSobe;
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
        hash += (sobaID != null ? sobaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Soba)) {
            return false;
        }
        Soba other = (Soba) object;
        if ((this.sobaID == null && other.sobaID != null) || (this.sobaID != null && !this.sobaID.equals(other.sobaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return brojSobe;
    }
    
}

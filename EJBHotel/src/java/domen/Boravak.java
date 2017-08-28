/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sanja
 */
@Entity
@Table(name = "boravak")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Boravak.findAll", query = "SELECT b FROM Boravak b"),
    @NamedQuery(name = "Boravak.findByBoravakID", query = "SELECT b FROM Boravak b WHERE b.boravakID = :boravakID"),
    @NamedQuery(name = "Boravak.findByDatumOd", query = "SELECT b FROM Boravak b WHERE b.datumOd = :datumOd"),
    @NamedQuery(name = "Boravak.findByDatumDo", query = "SELECT b FROM Boravak b WHERE b.datumDo = :datumDo"),
    @NamedQuery(name = "Boravak.findByCena", query = "SELECT b FROM Boravak b WHERE b.cena = :cena")})
public class Boravak implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "boravakID")
    private Integer boravakID;
    @Column(name = "datumOd")
    @Temporal(TemporalType.DATE)
    private Date datumOd;
    @Column(name = "datumDo")
    @Temporal(TemporalType.DATE)
    private Date datumDo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cena")
    private Double cena;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "boravakID")
    private List<Uplata> uplataList;
    @JoinColumn(name = "gostID", referencedColumnName = "gostID")
    @ManyToOne
    private Gost gostID;
    @JoinColumn(name = "recepcionerID", referencedColumnName = "recepcionerID")
    @ManyToOne
    private Recepcioner recepcionerID;
    @JoinColumn(name = "sobaID", referencedColumnName = "sobaID")
    @ManyToOne
    private Soba sobaID;

    public Boravak() {
    }

    public Boravak(Integer boravakID) {
        this.boravakID = boravakID;
    }

    public Integer getBoravakID() {
        return boravakID;
    }

    public void setBoravakID(Integer boravakID) {
        this.boravakID = boravakID;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    @XmlTransient
    public List<Uplata> getUplataList() {
        return uplataList;
    }

    public void setUplataList(List<Uplata> uplataList) {
        this.uplataList = uplataList;
    }

    public Gost getGostID() {
        return gostID;
    }

    public void setGostID(Gost gostID) {
        this.gostID = gostID;
    }

    public Recepcioner getRecepcionerID() {
        return recepcionerID;
    }

    public void setRecepcionerID(Recepcioner recepcionerID) {
        this.recepcionerID = recepcionerID;
    }

    public Soba getSobaID() {
        return sobaID;
    }

    public void setSobaID(Soba sobaID) {
        this.sobaID = sobaID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (boravakID != null ? boravakID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Boravak)) {
            return false;
        }
        Boravak other = (Boravak) object;
        if ((this.boravakID == null && other.boravakID != null) || (this.boravakID != null && !this.boravakID.equals(other.boravakID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Boravak[ boravakID=" + boravakID + " ]";
    }
    
}

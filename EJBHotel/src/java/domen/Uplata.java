/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sanja
 */
@Entity
@Table(name = "uplata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uplata.findAll", query = "SELECT u FROM Uplata u"),
    @NamedQuery(name = "Uplata.findByUplataID", query = "SELECT u FROM Uplata u WHERE u.uplataID = :uplataID"),
    @NamedQuery(name = "Uplata.findByDatumUplate", query = "SELECT u FROM Uplata u WHERE u.datumUplate = :datumUplate"),
    @NamedQuery(name = "Uplata.findByIznos", query = "SELECT u FROM Uplata u WHERE u.iznos = :iznos")})
public class Uplata implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "uplataID")
    private Integer uplataID;
    @Column(name = "datumUplate")
    @Temporal(TemporalType.DATE)
    private Date datumUplate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "iznos")
    private Double iznos;
    @JoinColumn(name = "boravakID", referencedColumnName = "boravakID")
    @ManyToOne
    private Boravak boravakID;

    public Uplata() {
    }

    public Uplata(Integer uplataID) {
        this.uplataID = uplataID;
    }

    public Integer getUplataID() {
        return uplataID;
    }

    public void setUplataID(Integer uplataID) {
        this.uplataID = uplataID;
    }

    public Date getDatumUplate() {
        return datumUplate;
    }

    public void setDatumUplate(Date datumUplate) {
        this.datumUplate = datumUplate;
    }

    public Double getIznos() {
        return iznos;
    }

    public void setIznos(Double iznos) {
        this.iznos = iznos;
    }

    public Boravak getBoravakID() {
        return boravakID;
    }

    public void setBoravakID(Boravak boravakID) {
        this.boravakID = boravakID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uplataID != null ? uplataID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uplata)) {
            return false;
        }
        Uplata other = (Uplata) object;
        if ((this.uplataID == null && other.uplataID != null) || (this.uplataID != null && !this.uplataID.equals(other.uplataID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Uplata[ uplataID=" + uplataID + " ]";
    }
    
}

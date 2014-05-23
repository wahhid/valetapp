/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.vo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.openswing.swing.message.receive.java.ValueObjectImpl;

/**
 *
 * @author root
 */
@Entity
@Table(name = "employeetype", catalog = "parkdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employeetype.findAll", query = "SELECT e FROM Employeetype e"),
    @NamedQuery(name = "Employeetype.findByEmployeetypeid", query = "SELECT e FROM Employeetype e WHERE e.employeetypeid = :employeetypeid"),
    @NamedQuery(name = "Employeetype.findByEmployeetypename", query = "SELECT e FROM Employeetype e WHERE e.employeetypename = :employeetypename"),
    @NamedQuery(name = "Employeetype.findByCreateddate", query = "SELECT e FROM Employeetype e WHERE e.createddate = :createddate"),
    @NamedQuery(name = "Employeetype.findByCreatedby", query = "SELECT e FROM Employeetype e WHERE e.createdby = :createdby"),
    @NamedQuery(name = "Employeetype.findByUpdateddate", query = "SELECT e FROM Employeetype e WHERE e.updateddate = :updateddate"),
    @NamedQuery(name = "Employeetype.findByUpdatedby", query = "SELECT e FROM Employeetype e WHERE e.updatedby = :updatedby")})
public class Employeetype extends ValueObjectImpl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "employeetypeid")
    private String employeetypeid;
    @Basic(optional = false)
    @Column(name = "employeetypename")
    private String employeetypename;
    @Basic(optional = false)
    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Basic(optional = false)
    @Column(name = "createdby")
    private String createdby;
    @Basic(optional = false)
    @Column(name = "updateddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateddate;
    @Basic(optional = false)
    @Column(name = "updatedby")
    private String updatedby;

    public Employeetype() {
    }

    public Employeetype(String employeetypeid) {
        this.employeetypeid = employeetypeid;
    }

    public Employeetype(String employeetypeid, String employeetypename, Date createddate, String createdby, Date updateddate, String updatedby) {
        this.employeetypeid = employeetypeid;
        this.employeetypename = employeetypename;
        this.createddate = createddate;
        this.createdby = createdby;
        this.updateddate = updateddate;
        this.updatedby = updatedby;
    }

    public String getEmployeetypeid() {
        return employeetypeid;
    }

    public void setEmployeetypeid(String employeetypeid) {
        this.employeetypeid = employeetypeid;
    }

    public String getEmployeetypename() {
        return employeetypename;
    }

    public void setEmployeetypename(String employeetypename) {
        this.employeetypename = employeetypename;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Date getUpdateddate() {
        return updateddate;
    }

    public void setUpdateddate(Date updateddate) {
        this.updateddate = updateddate;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeetypeid != null ? employeetypeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employeetype)) {
            return false;
        }
        Employeetype other = (Employeetype) object;
        if ((this.employeetypeid == null && other.employeetypeid != null) || (this.employeetypeid != null && !this.employeetypeid.equals(other.employeetypeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jakc.valetadmindesk.entity.Employeetype[ employeetypeid=" + employeetypeid + " ]";
    }
    
}

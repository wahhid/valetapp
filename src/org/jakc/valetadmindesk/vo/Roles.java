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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "roles", catalog = "parkdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r"),
    @NamedQuery(name = "Roles.findByRoleid", query = "SELECT r FROM Roles r WHERE r.roleid = :roleid"),
    @NamedQuery(name = "Roles.findByEmployeetypeid", query = "SELECT r FROM Roles r WHERE r.employeetypeid = :employeetypeid"),
    @NamedQuery(name = "Roles.findByFormid", query = "SELECT r FROM Roles r WHERE r.formid = :formid"),
    @NamedQuery(name = "Roles.findByCreaterecord", query = "SELECT r FROM Roles r WHERE r.createrecord = :createrecord"),
    @NamedQuery(name = "Roles.findByReadrecord", query = "SELECT r FROM Roles r WHERE r.readrecord = :readrecord"),
    @NamedQuery(name = "Roles.findByUpdaterecord", query = "SELECT r FROM Roles r WHERE r.updaterecord = :updaterecord"),
    @NamedQuery(name = "Roles.findByDeleterecord", query = "SELECT r FROM Roles r WHERE r.deleterecord = :deleterecord"),
    @NamedQuery(name = "Roles.findByCreateddate", query = "SELECT r FROM Roles r WHERE r.createddate = :createddate"),
    @NamedQuery(name = "Roles.findByCreatedby", query = "SELECT r FROM Roles r WHERE r.createdby = :createdby"),
    @NamedQuery(name = "Roles.findByUpdateddate", query = "SELECT r FROM Roles r WHERE r.updateddate = :updateddate"),
    @NamedQuery(name = "Roles.findByUpdatedby", query = "SELECT r FROM Roles r WHERE r.updatedby = :updatedby")})
public class Roles extends ValueObjectImpl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "roleid")
    private Integer roleid;
    @Basic(optional = false)
    @Column(name = "employeetypeid")
    private String employeetypeid;
    @Basic(optional = false)
    @Column(name = "formid")
    private String formid;
    @Basic(optional = false)
    @Column(name = "createrecord")
    private boolean createrecord;
    @Basic(optional = false)
    @Column(name = "readrecord")
    private boolean readrecord;
    @Basic(optional = false)
    @Column(name = "updaterecord")
    private boolean updaterecord;
    @Basic(optional = false)
    @Column(name = "deleterecord")
    private boolean deleterecord;
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

    public Roles() {
    }

    public Roles(Integer roleid) {
        this.roleid = roleid;
    }

    public Roles(Integer roleid, String employeetypeid, String formid, boolean createrecord, boolean readrecord, boolean updaterecord, boolean deleterecord, Date createddate, String createdby, Date updateddate, String updatedby) {
        this.roleid = roleid;
        this.employeetypeid = employeetypeid;
        this.formid = formid;
        this.createrecord = createrecord;
        this.readrecord = readrecord;
        this.updaterecord = updaterecord;
        this.deleterecord = deleterecord;
        this.createddate = createddate;
        this.createdby = createdby;
        this.updateddate = updateddate;
        this.updatedby = updatedby;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getEmployeetypeid() {
        return employeetypeid;
    }

    public void setEmployeetypeid(String employeetypeid) {
        this.employeetypeid = employeetypeid;
    }

    public String getFormid() {
        return formid;
    }

    public void setFormid(String formid) {
        this.formid = formid;
    }

    public boolean getCreaterecord() {
        return createrecord;
    }

    public void setCreaterecord(boolean createrecord) {
        this.createrecord = createrecord;
    }

    public boolean getReadrecord() {
        return readrecord;
    }

    public void setReadrecord(boolean readrecord) {
        this.readrecord = readrecord;
    }

    public boolean getUpdaterecord() {
        return updaterecord;
    }

    public void setUpdaterecord(boolean updaterecord) {
        this.updaterecord = updaterecord;
    }

    public boolean getDeleterecord() {
        return deleterecord;
    }

    public void setDeleterecord(boolean deleterecord) {
        this.deleterecord = deleterecord;
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
        hash += (roleid != null ? roleid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.roleid == null && other.roleid != null) || (this.roleid != null && !this.roleid.equals(other.roleid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jakc.valetadmindesk.entity.Roles[ roleid=" + roleid + " ]";
    }
    
}

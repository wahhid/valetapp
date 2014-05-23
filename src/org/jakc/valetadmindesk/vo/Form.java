/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.vo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.openswing.swing.message.receive.java.ValueObjectImpl;

/**
 *
 * @author root
 */
@Entity
@Table(name = "form", catalog = "parkdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Form.findAll", query = "SELECT f FROM Form f"),
    @NamedQuery(name = "Form.findByFormid", query = "SELECT f FROM Form f WHERE f.formid = :formid"),
    @NamedQuery(name = "Form.findByFormname", query = "SELECT f FROM Form f WHERE f.formname = :formname")})
public class Form extends ValueObjectImpl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "formid")
    private String formid;
    @Basic(optional = false)
    @Column(name = "formname")
    private String formname;

    public Form() {
    }

    public Form(String formid) {
        this.formid = formid;
    }

    public Form(String formid, String formname) {
        this.formid = formid;
        this.formname = formname;
    }

    public String getFormid() {
        return formid;
    }

    public void setFormid(String formid) {
        this.formid = formid;
    }

    public String getFormname() {
        return formname;
    }

    public void setFormname(String formname) {
        this.formname = formname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (formid != null ? formid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Form)) {
            return false;
        }
        Form other = (Form) object;
        if ((this.formid == null && other.formid != null) || (this.formid != null && !this.formid.equals(other.formid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jakc.valetadmindesk.entity.Form[ formid=" + formid + " ]";
    }
    
}

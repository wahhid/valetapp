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

/**
 *
 * @author wahhid
 */
@Entity
@Table(name = "vtransvalet", catalog = "parkdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vtransvalet.findAll", query = "SELECT v FROM Vtransvalet v"),
    @NamedQuery(name = "Vtransvalet.findByTransid", query = "SELECT v FROM Vtransvalet v WHERE v.transid = :transid"),
    @NamedQuery(name = "Vtransvalet.findByCarnumber", query = "SELECT v FROM Vtransvalet v WHERE v.carnumber = :carnumber"),
    @NamedQuery(name = "Vtransvalet.findByTranstype", query = "SELECT v FROM Vtransvalet v WHERE v.transtype = :transtype"),
    @NamedQuery(name = "Vtransvalet.findByDatetimein", query = "SELECT v FROM Vtransvalet v WHERE v.datetimein = :datetimein"),
    @NamedQuery(name = "Vtransvalet.findByTransdatein", query = "SELECT v FROM Vtransvalet v WHERE v.transdatein = :transdatein"),
    @NamedQuery(name = "Vtransvalet.findByOprin", query = "SELECT v FROM Vtransvalet v WHERE v.oprin = :oprin"),
    @NamedQuery(name = "Vtransvalet.findByBoothin", query = "SELECT v FROM Vtransvalet v WHERE v.boothin = :boothin"),
    @NamedQuery(name = "Vtransvalet.findByDriverin", query = "SELECT v FROM Vtransvalet v WHERE v.driverin = :driverin"),
    @NamedQuery(name = "Vtransvalet.findByShiftin", query = "SELECT v FROM Vtransvalet v WHERE v.shiftin = :shiftin"),
    @NamedQuery(name = "Vtransvalet.findByDatetimeout", query = "SELECT v FROM Vtransvalet v WHERE v.datetimeout = :datetimeout"),
    @NamedQuery(name = "Vtransvalet.findByTransdateout", query = "SELECT v FROM Vtransvalet v WHERE v.transdateout = :transdateout"),
    @NamedQuery(name = "Vtransvalet.findByOprout", query = "SELECT v FROM Vtransvalet v WHERE v.oprout = :oprout"),
    @NamedQuery(name = "Vtransvalet.findByBoothout", query = "SELECT v FROM Vtransvalet v WHERE v.boothout = :boothout"),
    @NamedQuery(name = "Vtransvalet.findByDriverout", query = "SELECT v FROM Vtransvalet v WHERE v.driverout = :driverout"),
    @NamedQuery(name = "Vtransvalet.findByShiftout", query = "SELECT v FROM Vtransvalet v WHERE v.shiftout = :shiftout"),
    @NamedQuery(name = "Vtransvalet.findByPricingid", query = "SELECT v FROM Vtransvalet v WHERE v.pricingid = :pricingid"),
    @NamedQuery(name = "Vtransvalet.findByHours", query = "SELECT v FROM Vtransvalet v WHERE v.hours = :hours"),
    @NamedQuery(name = "Vtransvalet.findByMinutes", query = "SELECT v FROM Vtransvalet v WHERE v.minutes = :minutes"),
    @NamedQuery(name = "Vtransvalet.findByValetcharge", query = "SELECT v FROM Vtransvalet v WHERE v.valetcharge = :valetcharge"),
    @NamedQuery(name = "Vtransvalet.findByParkingcharge", query = "SELECT v FROM Vtransvalet v WHERE v.parkingcharge = :parkingcharge"),
    @NamedQuery(name = "Vtransvalet.findByPinalty", query = "SELECT v FROM Vtransvalet v WHERE v.pinalty = :pinalty"),
    @NamedQuery(name = "Vtransvalet.findByPinaltycharge", query = "SELECT v FROM Vtransvalet v WHERE v.pinaltycharge = :pinaltycharge"),
    @NamedQuery(name = "Vtransvalet.findByTotalcharge", query = "SELECT v FROM Vtransvalet v WHERE v.totalcharge = :totalcharge"),
    @NamedQuery(name = "Vtransvalet.findByStatus", query = "SELECT v FROM Vtransvalet v WHERE v.status = :status"),
    @NamedQuery(name = "Vtransvalet.findByCreateddate", query = "SELECT v FROM Vtransvalet v WHERE v.createddate = :createddate"),
    @NamedQuery(name = "Vtransvalet.findByCreatedby", query = "SELECT v FROM Vtransvalet v WHERE v.createdby = :createdby"),
    @NamedQuery(name = "Vtransvalet.findByUpdateddate", query = "SELECT v FROM Vtransvalet v WHERE v.updateddate = :updateddate"),
    @NamedQuery(name = "Vtransvalet.findByUpdatedby", query = "SELECT v FROM Vtransvalet v WHERE v.updatedby = :updatedby")})
public class Vtransvalet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "transid")
    @Id
    private String transid;
    @Basic(optional = false)
    @Column(name = "carnumber")
    private String carnumber;
    @Basic(optional = false)
    @Column(name = "transtype")
    private int transtype;
    @Basic(optional = false)
    @Column(name = "datetimein")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetimein;
    @Column(name = "transdatein")
    @Temporal(TemporalType.DATE)
    private Date transdatein;
    @Basic(optional = false)
    @Column(name = "oprin")
    private String oprin;
    @Basic(optional = false)
    @Column(name = "boothin")
    private String boothin;
    @Basic(optional = false)
    @Column(name = "driverin")
    private String driverin;
    @Basic(optional = false)
    @Column(name = "shiftin")
    private String shiftin;
    @Column(name = "datetimeout")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetimeout;
    @Column(name = "transdateout")
    @Temporal(TemporalType.DATE)
    private Date transdateout;
    @Column(name = "oprout")
    private String oprout;
    @Column(name = "boothout")
    private String boothout;
    @Column(name = "driverout")
    private String driverout;
    @Column(name = "shiftout")
    private String shiftout;
    @Basic(optional = false)
    @Column(name = "pricingid")
    private int pricingid;
    @Basic(optional = false)
    @Column(name = "hours")
    private int hours;
    @Basic(optional = false)
    @Column(name = "minutes")
    private int minutes;
    @Basic(optional = false)
    @Column(name = "valetcharge")
    private double valetcharge;
    @Basic(optional = false)
    @Column(name = "parkingcharge")
    private double parkingcharge;
    @Basic(optional = false)
    @Column(name = "pinalty")
    private boolean pinalty;
    @Basic(optional = false)
    @Column(name = "pinaltycharge")
    private double pinaltycharge;
    @Basic(optional = false)
    @Column(name = "totalcharge")
    private double totalcharge;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Basic(optional = false)
    @Column(name = "createdby")
    private String createdby;
    @Column(name = "updateddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateddate;
    @Column(name = "updatedby")
    private String updatedby;

    public Vtransvalet() {
    }

    public String getTransid() {
        return transid;
    }

    public void setTransid(String transid) {
        this.transid = transid;
    }

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public int getTranstype() {
        return transtype;
    }

    public void setTranstype(int transtype) {
        this.transtype = transtype;
    }

    public Date getDatetimein() {
        return datetimein;
    }

    public void setDatetimein(Date datetimein) {
        this.datetimein = datetimein;
    }

    public Date getTransdatein() {
        return transdatein;
    }

    public void setTransdatein(Date transdatein) {
        this.transdatein = transdatein;
    }

    public String getOprin() {
        return oprin;
    }

    public void setOprin(String oprin) {
        this.oprin = oprin;
    }

    public String getBoothin() {
        return boothin;
    }

    public void setBoothin(String boothin) {
        this.boothin = boothin;
    }

    public String getDriverin() {
        return driverin;
    }

    public void setDriverin(String driverin) {
        this.driverin = driverin;
    }

    public String getShiftin() {
        return shiftin;
    }

    public void setShiftin(String shiftin) {
        this.shiftin = shiftin;
    }

    public Date getDatetimeout() {
        return datetimeout;
    }

    public void setDatetimeout(Date datetimeout) {
        this.datetimeout = datetimeout;
    }

    public Date getTransdateout() {
        return transdateout;
    }

    public void setTransdateout(Date transdateout) {
        this.transdateout = transdateout;
    }

    public String getOprout() {
        return oprout;
    }

    public void setOprout(String oprout) {
        this.oprout = oprout;
    }

    public String getBoothout() {
        return boothout;
    }

    public void setBoothout(String boothout) {
        this.boothout = boothout;
    }

    public String getDriverout() {
        return driverout;
    }

    public void setDriverout(String driverout) {
        this.driverout = driverout;
    }

    public String getShiftout() {
        return shiftout;
    }

    public void setShiftout(String shiftout) {
        this.shiftout = shiftout;
    }

    public int getPricingid() {
        return pricingid;
    }

    public void setPricingid(int pricingid) {
        this.pricingid = pricingid;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public double getValetcharge() {
        return valetcharge;
    }

    public void setValetcharge(double valetcharge) {
        this.valetcharge = valetcharge;
    }

    public double getParkingcharge() {
        return parkingcharge;
    }

    public void setParkingcharge(double parkingcharge) {
        this.parkingcharge = parkingcharge;
    }

    public boolean getPinalty() {
        return pinalty;
    }

    public void setPinalty(boolean pinalty) {
        this.pinalty = pinalty;
    }

    public double getPinaltycharge() {
        return pinaltycharge;
    }

    public void setPinaltycharge(double pinaltycharge) {
        this.pinaltycharge = pinaltycharge;
    }

    public double getTotalcharge() {
        return totalcharge;
    }

    public void setTotalcharge(double totalcharge) {
        this.totalcharge = totalcharge;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
    
}

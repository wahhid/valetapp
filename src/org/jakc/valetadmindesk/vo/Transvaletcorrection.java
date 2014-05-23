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
@Table(name = "transvaletcorrection", catalog = "parkdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transvaletcorrection.findAll", query = "SELECT t FROM Transvaletcorrection t"),
    @NamedQuery(name = "Transvaletcorrection.findById", query = "SELECT t FROM Transvaletcorrection t WHERE t.id = :id"),
    @NamedQuery(name = "Transvaletcorrection.findByTransid", query = "SELECT t FROM Transvaletcorrection t WHERE t.transid = :transid"),
    @NamedQuery(name = "Transvaletcorrection.findByCarnumber", query = "SELECT t FROM Transvaletcorrection t WHERE t.carnumber = :carnumber"),
    @NamedQuery(name = "Transvaletcorrection.findByTranstype", query = "SELECT t FROM Transvaletcorrection t WHERE t.transtype = :transtype"),
    @NamedQuery(name = "Transvaletcorrection.findByDatetimein", query = "SELECT t FROM Transvaletcorrection t WHERE t.datetimein = :datetimein"),
    @NamedQuery(name = "Transvaletcorrection.findByDatein", query = "SELECT t FROM Transvaletcorrection t WHERE t.datein = :datein"),
    @NamedQuery(name = "Transvaletcorrection.findByOprin", query = "SELECT t FROM Transvaletcorrection t WHERE t.oprin = :oprin"),
    @NamedQuery(name = "Transvaletcorrection.findByBoothin", query = "SELECT t FROM Transvaletcorrection t WHERE t.boothin = :boothin"),
    @NamedQuery(name = "Transvaletcorrection.findByDriverin", query = "SELECT t FROM Transvaletcorrection t WHERE t.driverin = :driverin"),
    @NamedQuery(name = "Transvaletcorrection.findByShiftin", query = "SELECT t FROM Transvaletcorrection t WHERE t.shiftin = :shiftin"),
    @NamedQuery(name = "Transvaletcorrection.findByDatetimeout", query = "SELECT t FROM Transvaletcorrection t WHERE t.datetimeout = :datetimeout"),
    @NamedQuery(name = "Transvaletcorrection.findByDateout", query = "SELECT t FROM Transvaletcorrection t WHERE t.dateout = :dateout"),
    @NamedQuery(name = "Transvaletcorrection.findByOprout", query = "SELECT t FROM Transvaletcorrection t WHERE t.oprout = :oprout"),
    @NamedQuery(name = "Transvaletcorrection.findByBoothout", query = "SELECT t FROM Transvaletcorrection t WHERE t.boothout = :boothout"),
    @NamedQuery(name = "Transvaletcorrection.findByDriverout", query = "SELECT t FROM Transvaletcorrection t WHERE t.driverout = :driverout"),
    @NamedQuery(name = "Transvaletcorrection.findByShiftout", query = "SELECT t FROM Transvaletcorrection t WHERE t.shiftout = :shiftout"),
    @NamedQuery(name = "Transvaletcorrection.findByPricingid", query = "SELECT t FROM Transvaletcorrection t WHERE t.pricingid = :pricingid"),
    @NamedQuery(name = "Transvaletcorrection.findByHours", query = "SELECT t FROM Transvaletcorrection t WHERE t.hours = :hours"),
    @NamedQuery(name = "Transvaletcorrection.findByMinutes", query = "SELECT t FROM Transvaletcorrection t WHERE t.minutes = :minutes"),
    @NamedQuery(name = "Transvaletcorrection.findByValet", query = "SELECT t FROM Transvaletcorrection t WHERE t.valet = :valet"),
    @NamedQuery(name = "Transvaletcorrection.findByValetcharge", query = "SELECT t FROM Transvaletcorrection t WHERE t.valetcharge = :valetcharge"),
    @NamedQuery(name = "Transvaletcorrection.findByValetcount", query = "SELECT t FROM Transvaletcorrection t WHERE t.valetcount = :valetcount"),
    @NamedQuery(name = "Transvaletcorrection.findByParkingcharge", query = "SELECT t FROM Transvaletcorrection t WHERE t.parkingcharge = :parkingcharge"),
    @NamedQuery(name = "Transvaletcorrection.findByVoucher", query = "SELECT t FROM Transvaletcorrection t WHERE t.voucher = :voucher"),
    @NamedQuery(name = "Transvaletcorrection.findByVoucherdetailid", query = "SELECT t FROM Transvaletcorrection t WHERE t.voucherdetailid = :voucherdetailid"),
    @NamedQuery(name = "Transvaletcorrection.findByVouchertype", query = "SELECT t FROM Transvaletcorrection t WHERE t.vouchertype = :vouchertype"),
    @NamedQuery(name = "Transvaletcorrection.findByVouchervalue", query = "SELECT t FROM Transvaletcorrection t WHERE t.vouchervalue = :vouchervalue"),
    @NamedQuery(name = "Transvaletcorrection.findByVouchercharge", query = "SELECT t FROM Transvaletcorrection t WHERE t.vouchercharge = :vouchercharge"),
    @NamedQuery(name = "Transvaletcorrection.findByVouchercount", query = "SELECT t FROM Transvaletcorrection t WHERE t.vouchercount = :vouchercount"),
    @NamedQuery(name = "Transvaletcorrection.findByPinalty", query = "SELECT t FROM Transvaletcorrection t WHERE t.pinalty = :pinalty"),
    @NamedQuery(name = "Transvaletcorrection.findByPinaltycharge", query = "SELECT t FROM Transvaletcorrection t WHERE t.pinaltycharge = :pinaltycharge"),
    @NamedQuery(name = "Transvaletcorrection.findByPinaltycount", query = "SELECT t FROM Transvaletcorrection t WHERE t.pinaltycount = :pinaltycount"),
    @NamedQuery(name = "Transvaletcorrection.findByTotalcharge", query = "SELECT t FROM Transvaletcorrection t WHERE t.totalcharge = :totalcharge"),
    @NamedQuery(name = "Transvaletcorrection.findByStatus", query = "SELECT t FROM Transvaletcorrection t WHERE t.status = :status"),
    @NamedQuery(name = "Transvaletcorrection.findByCreateddate", query = "SELECT t FROM Transvaletcorrection t WHERE t.createddate = :createddate"),
    @NamedQuery(name = "Transvaletcorrection.findByCreatedby", query = "SELECT t FROM Transvaletcorrection t WHERE t.createdby = :createdby"),
    @NamedQuery(name = "Transvaletcorrection.findByUpdateddate", query = "SELECT t FROM Transvaletcorrection t WHERE t.updateddate = :updateddate"),
    @NamedQuery(name = "Transvaletcorrection.findByUpdatedby", query = "SELECT t FROM Transvaletcorrection t WHERE t.updatedby = :updatedby")})
public class Transvaletcorrection extends ValueObjectImpl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "transid")
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
    @Basic(optional = false)
    @Column(name = "datein")
    @Temporal(TemporalType.DATE)
    private Date datein;
    @Basic(optional = false)
    @Column(name = "oprin")
    private String oprin;
    @Basic(optional = false)
    @Column(name = "boothin")
    private String boothin;
    @Column(name = "driverin")
    private String driverin;
    @Basic(optional = false)
    @Column(name = "shiftin")
    private String shiftin;
    @Column(name = "datetimeout")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetimeout;
    @Column(name = "dateout")
    @Temporal(TemporalType.DATE)
    private Date dateout;
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
    @Column(name = "valet")
    private boolean valet;
    @Basic(optional = false)
    @Column(name = "valetcharge")
    private double valetcharge;
    @Basic(optional = false)
    @Column(name = "valetcount")
    private short valetcount;
    @Basic(optional = false)
    @Column(name = "parkingcharge")
    private double parkingcharge;
    @Basic(optional = false)
    @Column(name = "voucher")
    private boolean voucher;
    @Column(name = "voucherdetailid")
    private String voucherdetailid;
    @Column(name = "vouchertype")
    private Integer vouchertype;
    @Basic(optional = false)
    @Column(name = "vouchervalue")
    private double vouchervalue;
    @Basic(optional = false)
    @Column(name = "vouchercharge")
    private double vouchercharge;
    @Basic(optional = false)
    @Column(name = "vouchercount")
    private short vouchercount;
    @Basic(optional = false)
    @Column(name = "pinalty")
    private boolean pinalty;
    @Basic(optional = false)
    @Column(name = "pinaltycharge")
    private double pinaltycharge;
    @Basic(optional = false)
    @Column(name = "pinaltycount")
    private short pinaltycount;
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

    public Transvaletcorrection() {
    }

    public Transvaletcorrection(Integer id) {
        this.id = id;
    }

    public Transvaletcorrection(Integer id, String transid, String carnumber, int transtype, Date datetimein, Date datein, String oprin, String boothin, String shiftin, int pricingid, int hours, int minutes, boolean valet, double valetcharge, short valetcount, double parkingcharge, boolean voucher, double vouchervalue, double vouchercharge, short vouchercount, boolean pinalty, double pinaltycharge, short pinaltycount, double totalcharge, int status, Date createddate, String createdby) {
        this.id = id;
        this.transid = transid;
        this.carnumber = carnumber;
        this.transtype = transtype;
        this.datetimein = datetimein;
        this.datein = datein;
        this.oprin = oprin;
        this.boothin = boothin;
        this.shiftin = shiftin;
        this.pricingid = pricingid;
        this.hours = hours;
        this.minutes = minutes;
        this.valet = valet;
        this.valetcharge = valetcharge;
        this.valetcount = valetcount;
        this.parkingcharge = parkingcharge;
        this.voucher = voucher;
        this.vouchervalue = vouchervalue;
        this.vouchercharge = vouchercharge;
        this.vouchercount = vouchercount;
        this.pinalty = pinalty;
        this.pinaltycharge = pinaltycharge;
        this.pinaltycount = pinaltycount;
        this.totalcharge = totalcharge;
        this.status = status;
        this.createddate = createddate;
        this.createdby = createdby;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getDatein() {
        return datein;
    }

    public void setDatein(Date datein) {
        this.datein = datein;
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

    public Date getDateout() {
        return dateout;
    }

    public void setDateout(Date dateout) {
        this.dateout = dateout;
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

    public boolean getValet() {
        return valet;
    }

    public void setValet(boolean valet) {
        this.valet = valet;
    }

    public double getValetcharge() {
        return valetcharge;
    }

    public void setValetcharge(double valetcharge) {
        this.valetcharge = valetcharge;
    }

    public short getValetcount() {
        return valetcount;
    }

    public void setValetcount(short valetcount) {
        this.valetcount = valetcount;
    }

    public double getParkingcharge() {
        return parkingcharge;
    }

    public void setParkingcharge(double parkingcharge) {
        this.parkingcharge = parkingcharge;
    }

    public boolean getVoucher() {
        return voucher;
    }

    public void setVoucher(boolean voucher) {
        this.voucher = voucher;
    }

    public String getVoucherdetailid() {
        return voucherdetailid;
    }

    public void setVoucherdetailid(String voucherdetailid) {
        this.voucherdetailid = voucherdetailid;
    }

    public Integer getVouchertype() {
        return vouchertype;
    }

    public void setVouchertype(Integer vouchertype) {
        this.vouchertype = vouchertype;
    }

    public double getVouchervalue() {
        return vouchervalue;
    }

    public void setVouchervalue(double vouchervalue) {
        this.vouchervalue = vouchervalue;
    }

    public double getVouchercharge() {
        return vouchercharge;
    }

    public void setVouchercharge(double vouchercharge) {
        this.vouchercharge = vouchercharge;
    }

    public short getVouchercount() {
        return vouchercount;
    }

    public void setVouchercount(short vouchercount) {
        this.vouchercount = vouchercount;
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

    public short getPinaltycount() {
        return pinaltycount;
    }

    public void setPinaltycount(short pinaltycount) {
        this.pinaltycount = pinaltycount;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transvaletcorrection)) {
            return false;
        }
        Transvaletcorrection other = (Transvaletcorrection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jakc.valetadmindesk.vo.Transvaletcorrection[ id=" + id + " ]";
    }
    
}

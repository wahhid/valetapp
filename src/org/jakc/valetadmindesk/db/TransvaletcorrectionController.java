/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.db;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.jakc.valetadmindesk.ErrHandling;
import org.jakc.valetadmindesk.PersistenceConnection;
import org.jakc.valetadmindesk.vo.Transvalet;
import org.jakc.valetadmindesk.vo.Transvaletcorrection;

/**
 *
 * @author root
 */
public class TransvaletcorrectionController extends PersistenceConnection {
    
    private ErrHandling eH = new ErrHandling();
    
    public TransvaletcorrectionController(){
        this.eH.setClassName("Transvaletcorrection");        
    }

    
    public List<Transvaletcorrection> getData(Date dateout){
        this.eH.setMethodName("getData");
        EntityManager em = this.getEntityManager();
        String strSQL = "SELECT t FROM Transvaletcorrection t WHERE t.dateout = :dateout ORDER BY t.transid";
        return em.createQuery(strSQL).setParameter("dateout", dateout).getResultList();
    }
    
    public void insert(Transvalet o) {
        this.eH.setMethodName("insert");        
        EntityManager em = this.getEntityManager();
        em.getTransaction().begin();
        try{
            Transvaletcorrection tvc = this.mappingFromTransvalet(o);
            em.persist(tvc);                    
            em.getTransaction().commit();
        }catch(Exception ex){
            this.eH.setErrStatus(1);
            this.eH.setErrMsg(ex.getMessage());
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
    }
    
    
    private Transvaletcorrection mappingFromTransvalet(Transvalet o){
        Transvaletcorrection tvc = new Transvaletcorrection();
        tvc.setTransid(o.getTransid());
        tvc.setCarnumber(o.getCarnumber());
        tvc.setTranstype(o.getTranstype());
        tvc.setDatetimein(o.getDatetimein());
        tvc.setDatein(o.getDatein());
        tvc.setOprin(o.getOprin());
        tvc.setBoothin(o.getBoothin());
        tvc.setDriverin(o.getDriverin());
        tvc.setShiftin(o.getShiftin());
        tvc.setDatetimeout(o.getDatetimeout());
        tvc.setDateout(o.getDateout());
        tvc.setOprout(o.getOprout());
        tvc.setBoothout(o.getBoothout());
        tvc.setDriverout(o.getBoothout());
        tvc.setShiftout(o.getShiftout());
        tvc.setPricingid(o.getPricingid());
        tvc.setHours(o.getHours());
        tvc.setMinutes(o.getMinutes());
        tvc.setValet(o.getValet());
        tvc.setValetcharge(o.getValetcharge());
        tvc.setValetcount(o.getValetcount());
        tvc.setParkingcharge(o.getParkingcharge());
        tvc.setVoucher(o.getVoucher());
        tvc.setVoucherdetailid(o.getVoucherdetailid());
        tvc.setVouchertype(o.getVouchertype());
        tvc.setVouchervalue(o.getVouchervalue());
        tvc.setVouchercharge(o.getVouchercharge());
        tvc.setVouchercount(o.getVouchercount());
        tvc.setPinalty(o.getPinalty());
        tvc.setPinaltycharge(o.getPinaltycharge());
        tvc.setPinaltycount(o.getPinaltycount());
        tvc.setTotalcharge(o.getTotalcharge());
        tvc.setStatus(o.getStatus());        
        tvc.setCreateddate(o.getCreateddate());
        tvc.setCreatedby(o.getCreatedby());
        tvc.setUpdateddate(o.getUpdateddate());
        tvc.setUpdatedby(o.getUpdatedby());                       
        return tvc;
    }

    public ErrHandling getErrHandling() {
        return eH;
    }

    public void setErrHandling(ErrHandling eH) {
        this.eH = eH;
    }
    
    
    
}

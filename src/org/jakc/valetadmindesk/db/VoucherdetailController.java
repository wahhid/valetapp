/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.db;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.jakc.valetadmindesk.PersistenceConnection;
import org.jakc.valetadmindesk.util.VoucherUtil;
import org.jakc.valetadmindesk.vo.Auth;
import org.jakc.valetadmindesk.vo.Voucher;
import org.jakc.valetadmindesk.vo.Voucherdetail;

/**
 *
 * @author wahhid
 */
public class VoucherdetailController extends PersistenceConnection{
    
    public VoucherdetailController(){
        
    }
    
    public void updateStatus(Voucherdetail voucherdetail){
        EntityManager em = this.getEntityManager();
        em.getTransaction().begin();
        try{
            em.merge(voucherdetail);
            em.getTransaction().commit();
        }catch(Exception ex){
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
    }
    
    public Voucherdetail getByID(String voucherdetailid){
        Voucherdetail voucherdetail = null;
        EntityManager em = this.getEntityManager();
        try{
            Query query = em.createNamedQuery("Voucherdetail.findByVouchdetailid")
                    .setParameter("vouchdetailid", voucherdetailid);
            voucherdetail = (Voucherdetail) query.getSingleResult();
        }catch(NoResultException ex){
            
        }finally{
            em.close();
        }
        return voucherdetail;        
    }
    
    
    public void generateVoucher(Voucher voucher,Auth auth){
        int i=0;
       
        EntityManager em = this.getEntityManager();
        em.getTransaction().begin();        
        try{                   
            while(i<voucher.getVouchernumbers()){
                Voucherdetail vo = new Voucherdetail();
                vo.setVouchdetailid(new VoucherUtil().getVoucherDetailID(voucher.getVoucherpref(), voucher.getVoucherdigit(), i));
                vo.setVoucherid(voucher.getVoucherid());
                vo.setCreateddate(new Date(System.currentTimeMillis()));
                vo.setCreatedby(auth.getNik());
                vo.setUpdateddate(new Date(System.currentTimeMillis()));
                vo.setUpdatedby(auth.getNik());               
                em.persist(vo);                
                i++;
            }        
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            em.close();
        }        
        
    }
       
}

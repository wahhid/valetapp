/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.db;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.jakc.valetadmindesk.PersistenceConnection;
import org.jakc.valetadmindesk.vo.Voucher;

/**
 *
 * @author wahhid
 */
public class VoucherController extends PersistenceConnection{
    
    public VoucherController(){
        
    }
        
    public void UpdateGeneratedStatus(Voucher voucher){
        EntityManager em = this.getEntityManager();
        em.getTransaction().begin();
        try{
            em.merge(voucher);
            em.getTransaction().commit();
        }catch(Exception ex){
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
    }
    
    public Voucher getByID(int voucherid){
        Voucher voucher = null;
        EntityManager em = this.getEntityManager();
        try{
            Query query = em.createNamedQuery("Voucher.findByVoucherid")
                    .setParameter("voucherid", voucherid);
            voucher = (Voucher) query.getSingleResult();
        }catch(NoResultException ex){
            
        }finally{
            em.close();
        }
        return voucher;
    }
}

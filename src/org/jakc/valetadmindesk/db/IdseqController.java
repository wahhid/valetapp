/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.db;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.jakc.valetadmindesk.PersistenceConnection;
import org.jakc.valetadmindesk.vo.Idseq;


/**
 *
 * @author wahhid
 */
public class IdseqController extends PersistenceConnection {
    
    public IdseqController(){
        
    }
    
    public Idseq getByBoothID(String boothid){
        Idseq idseq;
        EntityManager em = this.getEntityManager();
        try{            
            idseq = (Idseq) em.createNamedQuery("Idseq.findByBoothid").setParameter("boothid", boothid).getSingleResult();            
        }catch(NoResultException ex){
            idseq = null;
            System.out.println(ex.getMessage());
        }finally{
            em.close();
        }
        return idseq;
    }
    
    public void update(Idseq idseq){    
        EntityManager em = this.getEntityManager();
        em.getTransaction().begin();
        try{                        
            em.merge(idseq);            
            em.getTransaction().commit();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            em.close();
        }
    }
}

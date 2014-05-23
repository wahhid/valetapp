/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.db;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.jakc.valetadmindesk.PersistenceConnection;
import org.jakc.valetadmindesk.vo.Pricing;

/**
 *
 * @author wahhid
 */
public class PricingController extends PersistenceConnection{
    
    
    public PricingController(){
        
    }
    
    
    public List<Pricing> getAll(){
        EntityManager em = this.getEntityManager();
        Query query = em.createNamedQuery("Pricing.findAll");
        return query.getResultList();        
    }
    
    public Pricing getActivePricing(){
        Pricing pricing=null;
        EntityManager em = this.getEntityManager();
        try{            
            em.getTransaction().begin();
            pricing = (Pricing) em.createNamedQuery("Pricing.findByPricingenable").setParameter("pricingenable", true).getSingleResult();
            em.getTransaction().commit();            
        }catch(NoResultException ex){
            pricing = null;
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        return pricing;
    }    
    
    
}

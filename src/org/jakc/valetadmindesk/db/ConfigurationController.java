/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.db;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.jakc.valetadmindesk.PersistenceConnection;
import org.jakc.valetadmindesk.vo.Configuration;


/**
 *
 * @author root
 */
public class ConfigurationController extends PersistenceConnection {

    public ConfigurationController(){
        
    }
    
    public List<Configuration> getAll(){
        EntityManager em = this.getEntityManager();
        List<Configuration> o = new ArrayList();
        try{
            Query query = em.createNamedQuery("Configuration.findAll");
            o = query.getResultList();
        }catch(Exception ex){
            o = null;            
        }finally{
            em.close();
        }
        return o;
    }
    
    public Configuration getByID(String companyid){
        EntityManager em = this.getEntityManager();
        Configuration o;
        try{
            Query query = em.createNamedQuery("Configuration.findByCompanyid").setParameter("companyid", companyid);
            o = (Configuration) query.getSingleResult();           
        }catch(Exception ex){
            o = null;
        }finally{
            em.close();
        }
        return o;
    }
    
    public void update(Configuration o){
        EntityManager em = this.getEntityManager();
        em.getTransaction().begin();
        try{
            em.merge(o);            
            em.getTransaction().commit();
        }catch(Exception ex){
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
    }
}

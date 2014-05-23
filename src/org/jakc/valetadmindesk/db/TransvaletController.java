/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.db;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.jakc.valetadmindesk.ErrHandling;
import org.jakc.valetadmindesk.PersistenceConnection;
import org.jakc.valetadmindesk.vo.Transvalet;

/**
 *
 * @author wahhid
 */
public class TransvaletController extends PersistenceConnection {
    
    
    
    public TransvaletController(){
    
    }

    
    public Transvalet insert(Transvalet o) {        
        EntityManager em = this.getEntityManager();                
        em.getTransaction().begin();
        try{
            em.persist(o);
            em.getTransaction().commit();
        }catch(Exception ex){
            em.getTransaction().rollback();
            o = null;
        }
        return o;
    }
    
    public Transvalet update(Transvalet o){
        EntityManager em = this.getEntityManager();        
        em.getTransaction().begin();
        try{
            em.merge(o);
            em.getTransaction().commit();
        }catch(Exception ex){
            em.getTransaction().rollback();
            o = null;
        }
        return o;       
    }
    
    public Transvalet getByTransid(String transid){
        EntityManager em = this.getEntityManager();        
        Query query = em.createNamedQuery("Transvalet.findByTransid").setParameter("transid",transid);
        return (Transvalet) query.getSingleResult();                           
    }
                               
    public List<Transvalet> getData(Date DateOut,String BoothOut,String OperatorOut){
        List<Transvalet> transvalets = new ArrayList();                             
        String strDateOut = new SimpleDateFormat("yyyy-MM-dd").format(DateOut);
        System.out.println(strDateOut);
        String strSQL = "SELECT t FROM Transvalet t WHERE t.dateout = '" + strDateOut + "'";
        if(BoothOut != null || OperatorOut != null ){            
            if(BoothOut != null){
                strSQL = strSQL + " AND t.boothout = '" + BoothOut + "' ";                
            }            
            if(OperatorOut != null){
                strSQL = strSQL + " AND t.oprout = '" + OperatorOut + "' ";                
            }            
        }
        
        strSQL = strSQL + " ORDER BY t.oprout,t.boothout";
                
        System.out.println(strSQL);
        EntityManager em = this.getEntityManager();
        em.getTransaction().begin();
        try{                        
        
            Query query = em.createQuery(strSQL);
            transvalets = query.getResultList();
            System.out.println("Transvalets : "  + transvalets.size() + " Records");
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
        return transvalets;        
    }       
    
    public List<Transvalet> getDataCorrection(Date DateOut){
        List<Transvalet> transvalets = new ArrayList();                             
        String strDateOut = new SimpleDateFormat("yyyy-MM-dd").format(DateOut);        
        EntityManager em = this.getEntityManager();        
        try{                        
            String strSQL = "SELECT t FROM Transvalet t WHERE t.dateout='" + strDateOut + "' AND t.correction=1";           
            Query query = em.createQuery(strSQL);
            transvalets = query.getResultList();
            System.out.println("Transvalets : "  + transvalets.size() + " Records");        
        }catch(Exception ex){
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
        return transvalets;        
    }        
    
    public List<Transvalet> getData(Date StartDate,Date EndDate){
        List<Transvalet> transvalets = new ArrayList();                             
        String strStartDate = new SimpleDateFormat("yyyy-MM-dd").format(StartDate);
        String strEndDate = new SimpleDateFormat("yyyy-MM-dd").format(EndDate);                
        EntityManager em = this.getEntityManager();        
        try{                        
            String strSQL = "SELECT t FROM Transvalet t WHERE t.dateout BETWEEN '" + strStartDate + "' AND '" + strEndDate + "' ORDER BY t.dateout";             
            Query query = em.createQuery(strSQL);
            transvalets = query.getResultList();
            System.out.println("Transvalets : "  + transvalets.size() + " Records");        
        }catch(Exception ex){
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
        return transvalets;        
    }        
    
    public List<Transvalet> getVoucherUsage(Date usedate){
        EntityManager em = this.getEntityManager();
        String strUseDate = new SimpleDateFormat("yyyy-MM-dd").format(usedate);
        String strSQL = "SELECT t FROM Transvalet t WHERE t.dateout='" + strUseDate + "' AND t.voucher=1";
        return em.createQuery(strSQL).getResultList();
    }
    
    public Transvalet isCarExist(String carnumber){
        Transvalet transvalet;
        EntityManager em = this.getEntityManager();
        try{          
            String strSQL = "SELECT t from Transvalet t WHERE t.carnumber = :carnumber AND t.status = :status";
            transvalet = (Transvalet) em.createQuery(strSQL)
                    .setParameter("carnumber", carnumber)
                    .setParameter("status", 0)
                    .getSingleResult();                               
        }catch(NoResultException ex){
            transvalet = null;                       
        }finally{
            em.close();
        }
        return transvalet;
    }            
}

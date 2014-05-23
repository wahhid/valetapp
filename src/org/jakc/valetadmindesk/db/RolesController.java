/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.db;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.jakc.valetadmindesk.PersistenceConnection;
import org.jakc.valetadmindesk.vo.Roles;

/**
 *
 * @author root
 */
public class RolesController extends PersistenceConnection {
    
    public RolesController(){
        
    }
    
    public Roles getRole(String employeetypeid,String formid){
        Roles o = null;
        EntityManager em = this.getEntityManager();
        try{
            String strSQL = "SELECT r FROM Roles r WHERE r.employeetypeid = :employeetypeid AND r.formid = :formid";
            Query query = em.createQuery(strSQL);
            query.setParameter("employeetypeid", employeetypeid);
            query.setParameter("formid", formid);
            o = (Roles) query.getSingleResult();
        }catch(NoResultException ex){
            o = null;
        }finally{
            em.close();
        }
        return o;
    }
    
}

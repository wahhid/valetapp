/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.jakc.valetadmindesk.ErrHandling;
import org.jakc.valetadmindesk.PersistenceConnection;
import org.jakc.valetadmindesk.vo.Vouchertype;

/**
 *
 * @author wahhid
 */
public class VouchertypeController extends PersistenceConnection{


    public VouchertypeController(){
        
    }
        
    public List<Vouchertype> getAll(){
        EntityManager em = this.getEntityManager();
        Query query = em.createNamedQuery("Vouchertype.findAll");
        return query.getResultList();        
    }
}

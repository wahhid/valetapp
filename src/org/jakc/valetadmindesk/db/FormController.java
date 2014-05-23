/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.db;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.jakc.valetadmindesk.PersistenceConnection;
import org.jakc.valetadmindesk.vo.Form;

/**
 *
 * @author root
 */
public class FormController extends PersistenceConnection{
    
    public FormController(){
        
    }
    
    public List<Form> getAll(){        
        EntityManager em = this.getEntityManager();
        Query query = em.createNamedQuery("Form.findAll");
        return query.getResultList();
    }      
}

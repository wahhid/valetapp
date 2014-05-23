/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.db;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.jakc.valetadmindesk.ErrHandling;
import org.jakc.valetadmindesk.PersistenceConnection;
import org.jakc.valetadmindesk.vo.Employee;

/**
 *
 * @author wahhid
 */
public class EmployeeController extends PersistenceConnection {
 
    
    
    public EmployeeController(){
    
    }
        
    public List<Employee> getAll(){
        EntityManager em = this.getEntityManager();
        String strSQL = "SELECT e FROM Employee e ORDER BY e.fullname";
        Query query = em.createQuery(strSQL);
        return query.getResultList();
    }           
    
    public Employee find(String employeeid){
        EntityManager em = this.getEntityManager();
        Query query = em.createNamedQuery("Employee.findByEmployeeid").setParameter("employeeid", employeeid);
        return (Employee) query.getSingleResult();
    }
    
    public Employee update(Employee o){
        EntityManager em = this.getEntityManager();
        em.getTransaction().begin();
        try{
            em.merge(o);
            em.getTransaction().commit();
        }catch(Exception ex){
            o = null;            
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        return o;
    }
    
    public Employee checkLogin(String nik,String password){
        EntityManager em = this.getEntityManager();
        Employee e;
        try{
            String strSQL = "SELECT e FROM Employee e WHERE e.nik = :nik AND e.password = :password";        
            Query query = em.createQuery(strSQL);
            query.setParameter("nik", nik);
            query.setParameter("password", password);            
            e = (Employee) query.getSingleResult();                    
        }catch(NoResultException ex){
            e = null;
        }finally{
            em.close();
        }        
        return e ;
    }
}

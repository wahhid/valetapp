/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.jakc.valetadmindesk.db.BackingController;
import org.jakc.valetadmindesk.db.DomainController;
import org.jakc.valetadmindesk.db.EmployeeController;
import org.jakc.valetadmindesk.gui.PricingGridFrame;
import org.jakc.valetadmindesk.util.FormList;
import org.jakc.valetadmindesk.util.JPAUtils;
import org.jakc.valetadmindesk.vo.Auth;
import org.jakc.valetadmindesk.vo.Pricing;
import org.jakc.valetadmindesk.vo.Roles;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author wahhid
 */
public class PricingGridController extends GridController implements GridDataLocator{    
    private BackingController backingController;
    private DomainController domainController;
    private PricingGridFrame gridFrame;
  
    private final static String formid= FormList.FORM_MASTER_PRICING;
    private Auth auth;
    private Roles roles;
    
    public PricingGridController(Auth auth){
        this.auth = auth;
        this.backingController = new BackingController();
        this.domainController = new DomainController();        
        this.roles = this.backingController.getRoleController().getRole(this.auth.getEmployeetype(), this.formid);         
        this.gridFrame = new PricingGridFrame(this);
        MDIFrame.add(gridFrame);
    }
    
    private EntityManager getEntityManager(){
        return this.backingController.getEntityManager();
    }
        
    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        if(!this.roles.getReadrecord())
            return new ErrorResponse("Access Denied");
        
        EntityManager em = this.getEntityManager();
        try{
            String strSQL = "SELECT p FROM Pricing p";
            Object[] paramValues = new Object[]{};
            return JPAUtils.getBlockFromQuery(
                    action, 
                    startIndex, 
                    25, 
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    Pricing.class,
                    strSQL,
                    paramValues,
                    em);            
        }catch (Throwable ex){
            ex.printStackTrace();
            return new ErrorResponse(ex.getMessage());            
        }
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        if(!this.roles.getCreaterecord())
           return new ErrorResponse("Access Denied");
        
        EntityManager em = this.getEntityManager();
        em.getTransaction().begin();
        try{
            for(Object p: newValueObjects){               
                em.persist(p);
            }
            em.getTransaction().commit();
            return new VOListResponse(newValueObjects,false,newValueObjects.size());
        }catch(Throwable ex){
            ex.printStackTrace();
            em.getTransaction().rollback();
            return new ErrorResponse(ex.getMessage());
        }finally{            
            em.close();
        }
    }
    
    @Override
    public Response updateRecords(int[] rowNumbers,ArrayList oldPersistentObjects,ArrayList persistentObjects) throws Exception {                
        if(!this.roles.getUpdaterecord())
             return new ErrorResponse("Access Denied");
        
        EntityManager em = this.getEntityManager();
        em.getTransaction().begin();
        try{
            for(Object p: persistentObjects){
                Pricing vo = (Pricing) p;
                vo.setUpdateddate(new Timestamp(System.currentTimeMillis()));
                vo.setUpdatedby(this.auth.getNik());
                em.merge(vo);
            }
            em.getTransaction().commit();
            return new VOListResponse(persistentObjects,false,persistentObjects.size());
        }catch(Throwable ex){
            ex.printStackTrace();
            em.getTransaction().rollback();
            return new ErrorResponse(ex.getMessage());
        }finally{
            em.close();
        }
    }   
    
    public BackingController getBackingController() {
        return backingController;
    }

    public DomainController getDomainController() {
        return domainController;
    }

    
}

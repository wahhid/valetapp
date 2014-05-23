/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.controller;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;
import javax.persistence.EntityManager;
import org.jakc.valetadmindesk.db.BackingController;
import org.jakc.valetadmindesk.db.DomainController;
import org.jakc.valetadmindesk.gui.BoothGridFrame;
import org.jakc.valetadmindesk.util.FormList;
import org.jakc.valetadmindesk.util.JPAUtils;
import org.jakc.valetadmindesk.vo.Auth;
import org.jakc.valetadmindesk.vo.Booth;
import org.jakc.valetadmindesk.vo.Roles;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;


/**
 *
 * @author wahhid
 */
public class BoothGridController extends GridController implements GridDataLocator {    
    
    private BoothGridFrame gridFrame;          
    private BackingController backingController;
    private DomainController domainController;
    
    private Auth auth;
    private final static String formid = FormList.FORM_MASTER_BOOTH;
    private Roles roles;
    
    
    
    public BoothGridController(Auth auth){                
        this.auth = auth;        
        this.backingController = new BackingController();
        this.domainController = new DomainController();        
        this.roles = this.backingController.getRoleController().getRole(this.auth.getEmployeetype(), this.formid);               
        this.gridFrame = new BoothGridFrame(this);
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
            String strSQL = "SELECT b FROM Booth b";
            Object[] paramValues = new Object[]{};
            return JPAUtils.getBlockFromQuery(
                    action, 
                    startIndex, 
                    25, 
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    Booth.class,
                    strSQL,
                    paramValues,
                    em);            
        }catch (Throwable ex){
            ex.printStackTrace();
            return new ErrorResponse(ex.getMessage());            
        }finally{
            em.close();
        }                  
        
    }

    
    @Override
    public void createValueObject(ValueObject valuetObject) throws Exception{
        Booth vo = (Booth) valuetObject;
        vo.setCreateddate(new Timestamp(System.currentTimeMillis()));
        vo.setCreatedby(this.auth.getNik());        
        vo.setUpdateddate(new Timestamp(System.currentTimeMillis()));
        vo.setUpdatedby(this.auth.getNik());
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
                Booth booth = (Booth) p;
                booth.setUpdateddate(new Timestamp(System.currentTimeMillis()));
                booth.setUpdatedby(this.auth.getNik());
                em.merge(booth);
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

    public Roles getRoles() {
        return roles;
    }

    
    
}

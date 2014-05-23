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
import org.jakc.valetadmindesk.util.FormList;
import org.jakc.valetadmindesk.util.JPAUtils;
import org.jakc.valetadmindesk.vo.Auth;
import org.jakc.valetadmindesk.vo.Roles;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author root
 */
public class RoleGridController extends GridController implements GridDataLocator{

    private BackingController backingController;
    private DomainController domainController;    
    private Roles vo;
    private Auth auth;
    private String employeetypeid;    
    private Roles roles;
    private final static String formid = FormList.FORM_MASTER_ROLES;
    
    public RoleGridController(Auth auth){
        this.auth = auth;
        this.backingController = new BackingController();
        this.domainController = new DomainController();             
        this.roles = this.backingController.getRoleController().getRole(this.auth.getEmployeetype(), this.formid);
    }

    public String getEmployeetypeid() {
        return employeetypeid;
    }

    public void setEmployeetypeid(String employeetypeid) {
        this.employeetypeid = employeetypeid;
    }
       
    
    private EntityManager getEntityManager(){
        return this.backingController.getEntityManager();
    }
    
    
    @Override
    public void createValueObject(ValueObject valuetObject) throws Exception{
        Roles vo = (Roles) valuetObject;
        vo.setCreateddate(new Timestamp(System.currentTimeMillis()));
        vo.setCreatedby(this.auth.getNik());        
        vo.setUpdateddate(new Timestamp(System.currentTimeMillis()));
        vo.setUpdatedby(this.auth.getNik());
    }
    
    @Override
    public void selectedCell(int rowNumber,int columnIndex, String attributeName, ValueObject persistentObject){
        this.vo = (Roles) persistentObject;                             
    }
    
    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {       
        if(this.roles.getReadrecord() == false)
            return new ErrorResponse("Access Denied");    
        
        EntityManager em = this.getEntityManager();
        try{
            String strSQL = "SELECT r FROM Roles r WHERE r.employeetypeid = '" + this.employeetypeid + "'";
            Object[] paramValues = new Object[]{};
            return JPAUtils.getBlockFromQuery(
                    action, 
                    startIndex, 
                    25, 
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    Roles.class,
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
    public Response updateRecords(int[] rowNumbers,ArrayList oldPersistentObjects,ArrayList persistentObjects) throws Exception {                
        
        if(this.roles.getUpdaterecord() == false)
            return new ErrorResponse("Access Denied");      
        
        EntityManager em = this.getEntityManager();
        em.getTransaction().begin();
        try{
            for(Object p: persistentObjects){
                Roles vo = (Roles) p;
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
    
    
    
}

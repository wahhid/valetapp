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
import org.jakc.valetadmindesk.gui.EmployeeGridFrame;
import org.jakc.valetadmindesk.util.FormList;
import org.jakc.valetadmindesk.util.JPAUtils;
import org.jakc.valetadmindesk.vo.Auth;
import org.jakc.valetadmindesk.vo.Employee;
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
public class EmployeeGridController extends GridController implements GridDataLocator {

    
    private EmployeeGridFrame gridFrame;
    private BackingController backingController;
    private DomainController domainController;    
    private Auth auth;
    private final static String formid = FormList.FORM_REPORT_MASTER_EMPLOYEE;
    private Roles roles;
    private Employee vo;
    
    public EmployeeGridController(Auth auth){                
        this.auth = auth;
        this.backingController = new BackingController();
        this.domainController = new DomainController();
        this.roles = this.backingController.getRoleController().getRole(this.auth.getEmployeetype(), this.formid);               
        this.gridFrame = new EmployeeGridFrame(this);        
        MDIFrame.add(gridFrame);
    }
    
    
    @Override
    public void selectedCell(int rowNumber,int columnIndex, String attributeName, ValueObject persistentObject){
        this.vo = (Employee) persistentObject;         
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
            String strSQL = "SELECT e FROM Employee e";
            Object[] paramValues = new Object[]{};
            return JPAUtils.getBlockFromQuery(
                    action, 
                    startIndex, 
                    25, 
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    Employee.class,
                    strSQL,
                    paramValues,
                    em);            
        }catch (Throwable ex){
            ex.printStackTrace();
            return new ErrorResponse(ex.getMessage());          
        }
    }
           
    
    @Override
    public void createValueObject(ValueObject valuetObject) throws Exception{
        Employee vo = (Employee) valuetObject;
        vo.setCreateddate(new Timestamp(System.currentTimeMillis()));
        vo.setCreatedby(this.auth.getNik());        
    }    

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        if(!this.roles.getCreaterecord())
           return new ErrorResponse("Access Denied");        
        
        EntityManager em = this.getEntityManager();
        em.getTransaction().begin();
        try{
            for(Object p: newValueObjects)
                em.persist(p);
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
                Employee employee = (Employee) p;
                employee.setUpdateddate(new Timestamp(System.currentTimeMillis()));
                employee.setUpdatedby(this.auth.getNik());
                em.merge(employee);
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

    public Employee getVo() {
        return vo;
    }
    
    
    
}

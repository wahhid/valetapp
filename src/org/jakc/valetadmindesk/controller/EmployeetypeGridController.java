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
import org.jakc.valetadmindesk.gui.EmployeetypeGridFrame;
import org.jakc.valetadmindesk.util.FormList;
import org.jakc.valetadmindesk.util.JPAUtils;
import org.jakc.valetadmindesk.vo.Auth;
import org.jakc.valetadmindesk.vo.Employeetype;
import org.jakc.valetadmindesk.vo.Roles;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author root
 */
public class EmployeetypeGridController extends GridController implements GridDataLocator{

    private BackingController backingController;
    private DomainController domainController;
    private Employeetype vo;
    private EmployeetypeGridFrame frame;
    private Auth auth;
    private Roles roles;
    private final static String formid = FormList.FORM_MASTER_EMPLOYEE_TYPE;    
    
    public EmployeetypeGridController(Auth auth){
        this.auth = auth;
        this.backingController = new BackingController();
        this.domainController = new DomainController();        
        this.roles = this.backingController.getRoleController().getRole(this.auth.getEmployeetype(), this.formid);
        this.frame = new EmployeetypeGridFrame(this);
        MDIFrame.add(this.frame);
    }
    
    private EntityManager getEntityManager(){
        return this.backingController.getEntityManager();
    }
    
    @Override
    public void createValueObject(ValueObject valuetObject) throws Exception{
        Employeetype vo = (Employeetype) valuetObject;
        vo.setCreateddate(new Timestamp(System.currentTimeMillis()));
        vo.setCreatedby(this.auth.getNik());        
        vo.setUpdateddate(new Timestamp(System.currentTimeMillis()));
        vo.setUpdatedby(this.auth.getNik());
    }
    
   @Override
    public void selectedCell(int rowNumber,int columnIndex, String attributeName, ValueObject persistentObject){
        this.vo = (Employeetype) persistentObject;      
        this.frame.getRoleController().setEmployeetypeid(vo.getEmployeetypeid());
        this.frame.getGridControl2().reloadData();
    }    
    
    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        if(!this.roles.getReadrecord())
            return new ErrorResponse("Access Denied"); 
        
        EntityManager em = this.getEntityManager();
        try{
            String strSQL = "SELECT e FROM Employeetype e";
            Object[] paramValues = new Object[]{};
            return JPAUtils.getBlockFromQuery(
                    action, 
                    startIndex, 
                    25, 
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    Employeetype.class,
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

    public BackingController getBackingController() {
        return backingController;
    }

    public DomainController getDomainController() {
        return domainController;
    }

    public Employeetype getVo() {
        return vo;
    }

    public Auth getAuth() {
        return auth;
    }
    
    
    
}

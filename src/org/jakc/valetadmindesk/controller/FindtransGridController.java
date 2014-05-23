/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.controller;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.jakc.valetadmindesk.db.BackingController;
import org.jakc.valetadmindesk.db.DomainController;
import org.jakc.valetadmindesk.db.TransvaletcorrectionController;
import org.jakc.valetadmindesk.gui.FindtransGridFrame;
import org.jakc.valetadmindesk.util.FormList;
import org.jakc.valetadmindesk.util.JPAUtils;
import org.jakc.valetadmindesk.vo.Auth;
import org.jakc.valetadmindesk.vo.Roles;
import org.jakc.valetadmindesk.vo.Transvalet;
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
public class FindtransGridController extends GridController implements GridDataLocator {

    private EntityManagerFactory emf;
    private FindtransGridFrame gridFrame;
    private BackingController backingController;
    private DomainController domainController;
    private List<Transvalet> transvalets;
    private Transvalet vo;
    private Auth auth;
    private final static String formid = FormList.FORM_TRANSACTION_FIND;
    private Roles roles;    
    
                
    public FindtransGridController(Auth auth){
        this.backingController = new BackingController();
        this.domainController = new DomainController();
        this.auth = auth;
        this.roles = this.backingController.getRoleController().getRole(this.auth.getEmployeetype(), this.formid);                       
        this.gridFrame = new FindtransGridFrame(this);
        MDIFrame.add(gridFrame);
    }

    private EntityManager getEntityManager(){
        return this.backingController.getEntityManager();
    }
    
    @Override
    public void doubleClick(int rowNumber,ValueObject persistentObject) {
        Transvalet o = (Transvalet) persistentObject;        
        if(o.getCorrection() == 1){
            new TransvaletcorrectionGridController(o.getTransid());
        }
    }
    
    @Override
    public void selectedCell(int rowNumber,int columnIndex, String attributeName, ValueObject persistentObject){
        this.vo = (Transvalet) persistentObject;        
        this.gridFrame.getBtncorrection().setText("Correction : " + this.vo.getTransid());        
    }
    
    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {        
        if(!this.roles.getReadrecord())
            return new ErrorResponse("Access Denied");
        
        EntityManager em = this.getEntityManager();                                 
        try{
            String strSQL = "SELECT t FROM Transvalet t";
            Object[] paramValues = new Object[]{};
            return JPAUtils.getBlockFromQuery(
                    action, 
                    startIndex, 
                    25, 
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    Transvalet.class,
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
            for(Object p: persistentObjects)
                em.merge(p);
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
   
 
    @Override
    public Color getBackgroundColor(int row,String attributedName,Object value) {
        Transvalet o = (Transvalet) this.gridFrame.getGridControl1().getVOListTableModel().getObjectForRow(row);
        if(o.getCorrection() == 1){
            return new Color(241,143,137);
        }else{
            return super.getBackgroundColor(row,attributedName,value);
        }
                
    }
    
    public BackingController getBackingController() {
        return backingController;
    }

    public DomainController getDomainController() {
        return domainController;
    }

    public Transvalet getVo() {
        return vo;
    }

    public Auth getAuth() {
        return auth;
    }
               
}

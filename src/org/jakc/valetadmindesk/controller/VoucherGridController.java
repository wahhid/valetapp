/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.persistence.EntityManager;
import org.jakc.valetadmindesk.db.BackingController;
import org.jakc.valetadmindesk.gui.VoucherGridFrame;
import org.jakc.valetadmindesk.db.DomainController;
import org.jakc.valetadmindesk.util.FormList;
import org.jakc.valetadmindesk.util.JPAUtils;
import org.jakc.valetadmindesk.vo.Auth;
import org.jakc.valetadmindesk.vo.Roles;
import org.jakc.valetadmindesk.vo.Voucher;
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
public class VoucherGridController extends GridController implements GridDataLocator {

    private BackingController backingController;
    private DomainController domainController;
    private VoucherGridFrame frame;
    private Voucher voucher;
    private Auth auth;
    private Roles roles;
    private final static String formid = FormList.FORM_MASTER_VOUCHER;    
    
    public VoucherGridController(Auth auth){        
        this.auth = auth;
        this.backingController = new BackingController();
        this.domainController = new DomainController();
        this.roles = this.backingController.getRoleController().getRole(this.auth.getEmployeetype(), this.formid);        
        this.frame = new VoucherGridFrame(this);
        MDIFrame.add(frame);
    }
    
    private EntityManager getEntityManager(){
        return this.backingController.getEntityManager();
    }
    
    @Override
    public void selectedCell(int rowNumber,int columnIndex, String attributeName, ValueObject persistentObject){
        voucher = (Voucher) persistentObject;
        if(voucher.getGenerated()){
            this.frame.getBtngeneratevoucher().setEnabled(false);
        }else{
            this.frame.getBtngeneratevoucher().setEnabled(true);
        }
    }
  
    
    @Override
    public void doubleClick(int rowNumber,ValueObject persistentObject){
        voucher = (Voucher) persistentObject;
        new VoucherdetailGridController(voucher.getVoucherid());
    }
    
    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        if(!this.roles.getReadrecord())
            return new ErrorResponse("Access Denied");               
        
        EntityManager em = this.getEntityManager();
        try{
            String strSQL = "SELECT v FROM Voucher v";
            Object[] paramValues = new Object[]{};
            return JPAUtils.getBlockFromQuery(
                    action, 
                    startIndex, 
                    25, 
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    Voucher.class,
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
                Voucher o = (Voucher) p;
                o.setCreateddate(new Date(System.currentTimeMillis()));
                o.setCreatedby("007290");
                o.setUpdateddate(new Date(System.currentTimeMillis()));
                o.setUpdatedby("007290");                
                em.persist(o);
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
                em.merge(p);
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

    public Voucher getVoucher() {
        return voucher;
    }

    public Auth getAuth() {
        return auth;
    }

    

    
}

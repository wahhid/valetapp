/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.controller;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;
import org.jakc.valetadmindesk.db.BackingController;
import org.jakc.valetadmindesk.db.TransvaletController;
import org.jakc.valetadmindesk.gui.InputtransFormFrame;
import org.jakc.valetadmindesk.db.DomainController;
import org.jakc.valetadmindesk.services.FindShift;
import org.jakc.valetadmindesk.services.TransactionProcess;
import org.jakc.valetadmindesk.services.VoucherProcess;
import org.jakc.valetadmindesk.util.FormList;
import org.jakc.valetadmindesk.vo.Auth;
import org.jakc.valetadmindesk.vo.Pricing;
import org.jakc.valetadmindesk.vo.Roles;
import org.jakc.valetadmindesk.vo.Transvalet;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author wahhid
 */
public class InputtransFormController extends FormController {
        
    private InputtransFormFrame frame;    
    private BackingController backingController;
    private DomainController domainController;        
    private String transid;
    private Pricing pricing;
    private TransactionProcess transactionProcess;
    private VoucherProcess voucherProcess = new VoucherProcess();    
    private Auth auth;
    private final static String formid = FormList.FORM_TRANSACTION_MANUAL;
    private Roles roles;

    
    public InputtransFormController(Auth auth,String transid){
        this.auth = auth;
        this.transid = transid;
        System.out.println("Transid : " + this.transid);
        this.backingController = new BackingController();
        this.domainController = new DomainController();    
        this.pricing = this.backingController.getPricingController().getActivePricing();
        this.transactionProcess = new TransactionProcess();
        
        this.roles = this.backingController.getRoleController().getRole(this.auth.getEmployeetype(), this.formid);
        this.frame = new InputtransFormFrame(this);        
        MDIFrame.add(frame);        
        
        if(this.transid != null){
            this.frame.getForm1().setMode(Consts.READONLY);
            this.frame.getForm1().reload();
        }else{
            this.frame.getForm1().setMode(Consts.INSERT);
        }
        
    }                 
    
    private EntityManager getEntityManager(){
        return this.backingController.getEntityManager();
    }
    
    private TransvaletController getTransvaletController(){
        return this.backingController.getTransvaletController();
    }
    
    
    private void ValidateVoucher(String voucherid,Transvalet transvalet){        
        Short valetcount = 1;            
        if(voucherProcess.Validate(voucherid)){
            System.out.println("Voucher Validate Successfully");
            transvalet.setVoucher(true);                     
            transvalet.setValetcount(valetcount);
            transvalet.setVoucherdetailid(voucherid);
            transvalet.setVouchertype(voucherProcess.getVoucher().getVouchertype());
            transvalet.setVouchervalue(voucherProcess.getVoucher().getVouchervalue());            
        }else{
            System.out.println("Voucher Validate Error");
            transvalet.setVoucher(false);
            transvalet.setVoucherdetailid("");
        }
    }
    
    public boolean CheckTransID(String transid){
        boolean status = false;
        Transvalet transvalet = this.backingController.getTransvaletController().getByTransid(transid);
        if(transvalet!=null){
            status = true;
        }
        return status;
    }
    
    public boolean CarExist(String carnumber){
        boolean status = false;
        Transvalet transvalet = this.backingController.getTransvaletController().isCarExist(carnumber);
        if(transvalet!=null){
            status = true;
        }
        return status;
    }
    
    
    
    @Override
    public Response loadData(Class valueObjectClass){               
        
        try{           
            Transvalet vo = this.getTransvaletController().getByTransid(transid);            
            if(vo != null){
                this.frame.getDcdatein().setDate(vo.getDatein());
                this.frame.getDcdateout().setDate(vo.getDateout());
                
                String hourin = new SimpleDateFormat("HH").format(vo.getDatetimein());
                String minutein = new SimpleDateFormat("mm").format(vo.getDatetimein());
                this.frame.getCbhourin().getModel().setSelectedItem(hourin);
                this.frame.getCbminutein().getModel().setSelectedItem(minutein);
                
                if(vo.getStatus() == 1){
                    String hourout = new SimpleDateFormat("HH").format(vo.getDatetimeout());
                    String minuteout = new SimpleDateFormat("mm").format(vo.getDatetimeout());                
                    this.frame.getCbhourout().getModel().setSelectedItem(hourout);
                    this.frame.getCbminuteout().getModel().setSelectedItem(minuteout);           
                }
                
                return new VOResponse(vo);                 
            }else{
                return new ErrorResponse("Data not Found");
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return new ErrorResponse(ex.getMessage());
        }
    }
    
    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception{
         try{
            if(!this.roles.getReadrecord())
                return new ErrorResponse("Access Denied");
           
            Transvalet o = (Transvalet) newPersistentObject;                 
                                    
            //Complete Date Time IN
            String strDatetimein = new SimpleDateFormat("yyyy-MM-dd").format(this.frame.getDcdatein().getDate());
            strDatetimein = strDatetimein + " " + this.frame.getCbhourin().getModel().getSelectedItem().toString() + ":" + this.frame.getCbminutein().getModel().getSelectedItem().toString() + ":00";
            Date datetimein = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDatetimein);
            o.setDatetimein(datetimein);
            o.setDatein(this.frame.getDcdatein().getDate());

            //Complete Date Time OUT
            String strDatetimeout = new SimpleDateFormat("yyyy-MM-dd").format(this.frame.getDcdateout().getDate());
            strDatetimeout = strDatetimeout + " " + this.frame.getCbhourout().getModel().getSelectedItem().toString() + ":" + this.frame.getCbminuteout().getModel().getSelectedItem().toString() + ":00";
            Date datetimeout = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDatetimeout);
            o.setDatetimeout(datetimeout);
            FindShift findshift = new FindShift();
            findshift.setStartdate(datetimein);
            findshift.setEnddate(datetimeout);
            findshift.Find();
            o.setDateout(findshift.getTransdate());
            o.setCarnumber(o.getCarnumber().toLowerCase());

            if(o.getVoucher()){
                this.ValidateVoucher(o.getVoucherdetailid(), o);
            }

            this.transactionProcess.calculateDuration(o);
            this.transactionProcess.calculateAmount(o, pricing);
            o.setStatus(1);
            o.setCreatedby(this.auth.getNik());
            o.setCreateddate(new Date(System.currentTimeMillis()));
            o.setUpdatedby(this.auth.getNik());
            o.setUpdateddate(new Date(System.currentTimeMillis()));        
                
            Transvalet vo = this.getBackingController().getTransvaletController().insert(o);
            if(vo != null){
                return new VOResponse(vo);
            }else{
                return new ErrorResponse("Error Insert Data");
            }            
        }catch(Exception ex){
            return new ErrorResponse(ex.getMessage());
        }
        
    }    
    
    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception{
        try{            
            Transvalet o = (Transvalet) persistentObject;            
            Transvalet old = (Transvalet) oldPersistentObject;
            
            //Complete Date Time IN
            String strDatetimein = new SimpleDateFormat("yyyy-MM-dd").format(this.frame.getDcdatein().getDate());
            strDatetimein = strDatetimein + " " + this.frame.getCbhourin().getModel().getSelectedItem().toString() + ":" + this.frame.getCbminutein().getModel().getSelectedItem().toString() + ":00";
            Date datetimein = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDatetimein);
            o.setDatetimein(datetimein);
            o.setDatein(this.frame.getDcdatein().getDate());

            //Complete Date Time OUT
            String strDatetimeout = new SimpleDateFormat("yyyy-MM-dd").format(this.frame.getDcdateout().getDate());
            strDatetimeout = strDatetimeout + " " + this.frame.getCbhourout().getModel().getSelectedItem().toString() + ":" + this.frame.getCbminuteout().getModel().getSelectedItem().toString() + ":00";
            Date datetimeout = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDatetimeout);
            o.setDatetimeout(datetimeout);            
            FindShift findshift = new FindShift();
            findshift.setStartdate(datetimein);
            findshift.setEnddate(datetimeout);
            findshift.Find();
            o.setDateout(findshift.getTransdate());

            if(o.getVoucher()){
                System.out.println("Calculate Voucher");
                this.ValidateVoucher(o.getVoucherdetailid(), o);
            }        

            this.transactionProcess.calculateDuration(o);
            this.transactionProcess.calculateAmount(o, pricing);

            o.setStatus(1);
            short correction=1;
            o.setCorrection(correction);
            o.setUpdatedby(this.auth.getNik());
            o.setUpdateddate(new Date(System.currentTimeMillis()));              
            
            Transvalet vo = this.getBackingController().getTransvaletController().update(o);
            if(vo != null){
                this.backingController.getTransvaletcorrectionController().insert(old);
                if(this.backingController.getTransvaletcorrectionController().getErrHandling().getErrStatus() == 1){
                    this.backingController.getTransvaletcorrectionController().getErrHandling().setErrStatus(0);
                    return new ErrorResponse("Error Update Correction");
                }
                return new VOResponse(vo);
            }else{
                return new ErrorResponse("Error Update Data");
            }            
        }catch(Exception ex){
            return new ErrorResponse(ex.getMessage());
        }        
        
    }
  
    public BackingController getBackingController() {
        return backingController;
    }

    public DomainController getDomainController() {
        return domainController;
    }

    public Pricing getPricing() {
        return pricing;
    }
    

    
}

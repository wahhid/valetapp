/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.services;

import java.util.Date;
import org.jakc.valetadmindesk.db.BackingController;
import org.jakc.valetadmindesk.db.VoucherController;
import org.jakc.valetadmindesk.db.VoucherdetailController;
import org.jakc.valetadmindesk.vo.Voucher;
import org.jakc.valetadmindesk.vo.Voucherdetail;
/**
 *
 * @author wahhid
 */
public class VoucherProcess { 

    private BackingController backingController;
    
    private Voucher voucher;
    private Voucherdetail voucherdetail;    
    
    private String message;
    
    public VoucherProcess(){
        this.backingController = new BackingController();
    }
    
    private VoucherController getVoucherController(){
        return this.backingController.getVoucherController();
    }
    private VoucherdetailController getVoucherdetailController(){
        return this.backingController.getVoucherdetailController();
    }
    
    public boolean Validate(String voucherdetailid){
        boolean isvalid = false;        
        Date currentDate = new Date(System.currentTimeMillis());
        voucherdetail = this.getVoucherdetail(voucherdetailid);
        if(voucherdetail != null && voucherdetail.getStatus() == false){
            voucher = this.getVoucher(voucherdetail.getVoucherid());
            if(voucher != null && voucher.getStatus()==true){
                if(currentDate.after(voucher.getVoucherstartdate()) && currentDate.before(voucher.getVoucherenddate())){
                    isvalid = true;
                    voucherdetail.setStatus(true);
                    voucherdetail.setVoucherusedate(new Date(System.currentTimeMillis()));
                    this.backingController.getVoucherdetailController().updateStatus(voucherdetail);                    
                }else{
                    System.out.print("Voucher Expired");
                }
            }else{
                System.out.println("Voucher Not Found");
            }
        }else{
            System.out.println("Voucher Not Found or Expired");
        }
        return isvalid;
    }
    
    

    public boolean Validate(String voucherdetailid,Date currentDate){
        boolean isvalid = false;                
        voucherdetail = this.getVoucherdetail(voucherdetailid);
        if(voucherdetail != null && voucherdetail.getStatus() == false){
            voucher = this.getVoucher(voucherdetail.getVoucherid());
            if(voucher != null && voucher.getStatus()==true){
                if(currentDate.after(voucher.getVoucherstartdate()) && currentDate.before(voucher.getVoucherenddate())){
                    isvalid = true;
                    voucherdetail.setStatus(true);
                    voucherdetail.setVoucherusedate(new Date(System.currentTimeMillis()));
                    this.backingController.getVoucherdetailController().updateStatus(voucherdetail);                                        
                }else{
                    this.message = "Voucher Expired";
                }
            }else{
                this.message = "Voucher Not Found";
            }
        }else{
            this.message = "Voucher Not Found or Expired";
        }
        return isvalid;
    }
    
    public void updateVoucherDetail(Voucherdetail voucherdetail){
        this.getVoucherdetailController().updateStatus(voucherdetail);
    }
    
    private Voucherdetail getVoucherdetail(String voucherdetailid){
        Voucherdetail o;
        o = this.getVoucherdetailController().getByID(voucherdetailid);
        return o;
    }
    
    private Voucher getVoucher(int voucher){
        Voucher o;
        o = this.getVoucherController().getByID(voucher);
        return o;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public Voucherdetail getVoucherdetail() {
        return voucherdetail;
    }

    public String getMessage() {
        return message;
    }
           
    
}

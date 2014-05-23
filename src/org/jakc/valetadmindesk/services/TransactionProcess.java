/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.services;

import org.jakc.valetadmindesk.db.BackingController;
import org.jakc.valetadmindesk.vo.Pricing;
import org.jakc.valetadmindesk.vo.Transvalet;


/**
 *
 * @author wahhid
 */
public class TransactionProcess {
    
    private BackingController backingController;
    private CheckPinalty checkPinalty;
     
    public TransactionProcess(){
        this.checkPinalty = new CheckPinalty();
        this.backingController = new BackingController();
        
    }                     
    public void calculateDuration(Transvalet transvalet){
        long milis1 = transvalet.getDatetimein().getTime();
        long milis2 = transvalet.getDatetimeout().getTime();
        
        long diff = milis2 - milis1;
        
        long diffMinutes = diff / (60 * 1000);
        long diffHours = diff / (60 * 60 * 1000);
        
        String strMinutes = Long.toString(diffMinutes - (diffHours * 60));
        String strHours = Long.toString(diffHours);
                
        transvalet.setMinutes(Integer.parseInt(strMinutes));
        transvalet.setHours(Integer.parseInt(strHours));
        
    }       
    public void calculateAmount(Transvalet transvalet,Pricing pricing){        
        int totalHours = 0;
        if(pricing != null){
            
            if(transvalet.getMinutes() > 0){               
                totalHours = transvalet.getHours() + 1;
            }else{
                totalHours = transvalet.getHours();
            }
            
            transvalet.setPricingid(pricing.getPricingid());            
            //Calculate Parking Charge
            if(pricing.getPricingenable()){                
                transvalet.setParkingcharge(totalHours * pricing.getPricinghourly());
            }
            //Calculate Valet Charge
            if(pricing.getPricingservicesenable()){
                Short valetcount = 1;
                transvalet.setValetcount(valetcount);
                transvalet.setValetcharge(pricing.getPricingservices());
            }
                                                
            if(transvalet.getVoucher() == true){                                
                if(transvalet.getVouchertype() == 1){
                    if(pricing.getPricingenable()){                        
                        transvalet.setVouchercharge(transvalet.getParkingcharge() * transvalet.getVouchervalue()  / 100);                                                
                    }
                }
                
                if(transvalet.getVouchertype() == 2){
                    if(pricing.getPricingenable()){               
                        Double diffValue = transvalet.getParkingcharge() - transvalet.getVouchervalue();
                        if(diffValue < 0){
                            transvalet.setVouchercharge(transvalet.getParkingcharge());
                        }else{
                            transvalet.setVouchercharge(transvalet.getVouchervalue());
                        }                        
                    }
                }                                
            }        
            
            if(transvalet.getReciept()){
                Short value = 1;
                if(pricing.getMissingticketenable()){
                    transvalet.setReceiptcharge(pricing.getMissingticketcharge());
                    transvalet.setReceiptcount(value);
                }
            }
        }
        
        this.checkPinalty.Validate(transvalet, pricing);        
        
        transvalet.setTotalcharge(transvalet.getParkingcharge() + transvalet.getValetcharge() + transvalet.getReceiptcharge() + transvalet.getPinaltycharge() - transvalet.getVouchercharge());
        
    }
    
}

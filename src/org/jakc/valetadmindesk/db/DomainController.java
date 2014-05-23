/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.db;

import java.util.List;
import org.jakc.valetadmindesk.PersistenceConnection;
import org.jakc.valetadmindesk.vo.Booth;
import org.jakc.valetadmindesk.vo.Employee;
import org.jakc.valetadmindesk.vo.Form;
import org.jakc.valetadmindesk.vo.Pricing;
import org.jakc.valetadmindesk.vo.Shift;
import org.jakc.valetadmindesk.vo.Vouchertype;
import org.openswing.swing.domains.java.Domain;

/**
 *
 * @author wahhid
 */
public class DomainController extends BackingController{
    
    
    public DomainController(){        
        
    }
    
    
    public Domain getCBShift(){
        Domain d = new Domain("SHIFT");
        List<Shift> shifts = this.shiftController.getAll();
        for(Shift shift: shifts){
            d.addDomainPair(shift.getShiftid(), shift.getShiftname());
        }
        return d;
        
    }
    
    public Domain getCBForm(){
        Domain d = new Domain("FORM");
        List<Form> forms = this.formController.getAll();
        for(Form form : forms){
            d.addDomainPair(form.getFormid(), form.getFormname());
        }
        return d;
        
    }
    
    public Domain getCBBoothAll(){
        Domain d = new Domain("BOOTH");
        List<Booth> booths = this.boothController.getAll();
        if(booths.size()>0){
            d.addDomainPair("All","All");
            for(Booth booth : booths){               
                d.addDomainPair(booth.getBoothid(),booth.getBoothname());
            }                        
        }else{
            d.addDomainPair("Nodata","No Data");
        }
        return d;        
    }
    
    public Domain getCBBooth(){
        Domain d = new Domain("BOOTH");
        List<Booth> booths = this.boothController.getAll();
        if(booths.size()>0){            
            for(Booth booth : booths){               
                d.addDomainPair(booth.getBoothid(),booth.getBoothname());
            }                        
        }else{
            d.addDomainPair("Nodata","No Data");
        }
        return d;
    }
    
    public Domain getCBEmployeeAll(){
        Domain d = new Domain("EMPLOYEE");
        List<Employee> employees = this.employeeController.getAll();
        if(employees.size()>0){
            d.addDomainPair("All","All");
            for(Employee employee : employees){
                d.addDomainPair(employee.getNik(), employee.getFullname());
            }            
        }else{
            d.addDomainPair("Nodata","No Data");
        }
        return d;        
    }
    
    public Domain getCBEmployee(){
        Domain d = new Domain("EMPLOYEE");
        List<Employee> employees = this.employeeController.getAll();
        if(employees.size()>0){            
            for(Employee employee : employees){
                d.addDomainPair(employee.getNik(), employee.getFullname());
            }            
        }else{
            d.addDomainPair("Nodata","No Data");
        }
        return d;
    }

    public Domain getCBVouchertype(){
        Domain d = new Domain("VOUCHERTYPE");
        List<Vouchertype> vouchertypes = this.vouchertypeController.getAll();
        for(Vouchertype vouchertype : vouchertypes){
            d.addDomainPair(vouchertype.getVouchertypeid(), vouchertype.getVouchertypename());
        }
        return d;        
    }
    
    public Domain getCBActive(){
        Domain d = new Domain("ACTIVE");
        d.addDomainPair(0, "Not Active");
        d.addDomainPair(1, "Active");
        return d;
    }

    public Domain getCBYes(){
        Domain d = new Domain("YES");
        d.addDomainPair(0, "No");
        d.addDomainPair(1, "Yes");
        return d;
    }
   
    public Domain getCBTrue(){
        Domain d = new Domain("TRUE");
        d.addDomainPair(0, "False");
        d.addDomainPair(1, "True");
        return d;
    }    
    
    public Domain getCBEnabledisable(){
        Domain d = new Domain("STATUS") ;
        d.addDomainPair(0, "Disable");
        d.addDomainPair(1, "Enable");
        return d;
    }    
    
    public Domain getCBStatus(){
        Domain d = new Domain("CARSTATUS") ;
        d.addDomainPair(0, "In");
        d.addDomainPair(1, "Out");
        return d;
    }        
    
    public Domain getCBEmployeeType(){
        Domain d = new Domain("EMPLOYEETYPE") ;
        d.addDomainPair("01", "Administrator");
        d.addDomainPair("02", "Admin");
        d.addDomainPair("03", "Operator");
        d.addDomainPair("04", "Driver");
        return d;
    }    
    
    public Domain getCBTranstype(){
        Domain d = new Domain("TRANSTYPE");
        d.addDomainPair(0, "Default");
        d.addDomainPair(1, "Manual");
        return d;
    }    
        
    public Domain getCBPricing(){
        Domain d = new Domain("PRICING");
        List<Pricing> pricings = this.pricingController.getAll();
        for(Pricing pricing : pricings){
            d.addDomainPair(pricing.getPricingid(),pricing.getPricingname());
        }
        return d;           
    }
}

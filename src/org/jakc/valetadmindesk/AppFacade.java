/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jakc.valetadmindesk;


import org.jakc.valetadmindesk.controller.BoothGridController;
import org.jakc.valetadmindesk.controller.ConfigurationFormController;
import org.jakc.valetadmindesk.controller.DetailPerOperatorReportController;
import org.jakc.valetadmindesk.controller.EmployeeGridController;
import org.jakc.valetadmindesk.controller.EmployeetypeGridController;
import org.jakc.valetadmindesk.controller.FindtransGridController;
import org.jakc.valetadmindesk.controller.InputtransFormController;
import org.jakc.valetadmindesk.controller.PricingGridController;
import org.jakc.valetadmindesk.controller.ShiftGridController;
import org.jakc.valetadmindesk.controller.VoucherGridController;
import org.jakc.valetadmindesk.controller.report.CorrectionFormController;
import org.jakc.valetadmindesk.controller.report.DetailDailyValetFormController;
import org.jakc.valetadmindesk.controller.report.SummaryDailyOperatorFormController;
import org.jakc.valetadmindesk.controller.report.SummaryMonthlyValetFormController;
import org.jakc.valetadmindesk.controller.report.VoucheruseFormController;
import org.jakc.valetadmindesk.vo.Auth;
import org.openswing.swing.mdi.client.ClientFacade;


/**
 *
 * @author wahhid
 */
public class AppFacade implements ClientFacade {
            
    private Auth auth;
    
    public AppFacade(Auth auth){
        this.auth = auth;
    }
    
    public void getBooth(){
        new BoothGridController(this.auth);
    }
    
    public void getEmployee(){
        new EmployeeGridController(this.auth);
    }
    

    public void getEmployeeType(){
        new EmployeetypeGridController(this.auth);
    }
    
    public void getFind(){
        new FindtransGridController(this.auth);
    }

    public void getPricing(){
        new PricingGridController(this.auth);
    }
    
    public void getShift(){
        new ShiftGridController(this.auth);
    }
    
    public void getVoucher(){
        new VoucherGridController(this.auth);
    }
    
    public void getConfiguration(){
        new ConfigurationFormController(this.auth);
    }
    
    public void getManual(){
        new InputtransFormController(this.auth,null);
    }
    
    public void getDailyReport(){
        new DetailPerOperatorReportController();
    }
    
    public void getDetailDailyReport(){
        new DetailDailyValetFormController(this.auth);
    }
    
    public void getPerOperatorDailyReport(){
        new SummaryDailyOperatorFormController(this.auth);
    }
        
    public void getMonthlyReport(){
        new SummaryMonthlyValetFormController(this.auth);
    }
    
    public void getCorrectionReport(){
        new CorrectionFormController(this.auth);
    }
    
    public void getVoucherUsage(){
        new VoucheruseFormController(this.auth);
    }
    
}


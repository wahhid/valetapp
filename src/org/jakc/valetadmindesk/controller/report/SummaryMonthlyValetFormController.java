/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.controller.report;

import java.util.Date;
import java.util.List;
import org.jakc.valetadmindesk.db.BackingController;
import org.jakc.valetadmindesk.db.DomainController;
import org.jakc.valetadmindesk.gui.report.SummaryMonthlyValetFormFrame;
import org.jakc.valetadmindesk.vo.Auth;
import org.jakc.valetadmindesk.vo.Transvalet;
import org.openswing.swing.mdi.client.MDIFrame;

/**
 *
 * @author root
 */
public class SummaryMonthlyValetFormController {
    
    private BackingController backingController;
    private DomainController domainController;    
    private SummaryMonthlyValetFormFrame frame;    
    private Auth auth;
    private String filePath;
    
    
    public SummaryMonthlyValetFormController(Auth auth){
        this.backingController = new BackingController();
        this.domainController = new DomainController();
        this.auth = auth;                
        this.frame = new SummaryMonthlyValetFormFrame(this);       
        //filePath = getClass().getResource("/org/jakc/valetadmindesk/resource/report/rptmonthlyvaletsummary.jasper").getPath();
        filePath = this.auth.getReportpath() + "/rptmonthlyvaletsummary.jasper"; 
        MDIFrame.add(this.frame);        
    }

    public DomainController getDomainController() {
        return domainController;
    }
    
    
    public List<Transvalet> generateReport(Date startdate, Date enddate){
        List<Transvalet> os = this.backingController.getTransvaletController().getData(startdate,enddate);
        return os;
    }    
    
    public String getFilePath() {
        return filePath;
    }

    public Auth getAuth() {
        return auth;
    }
    
    
    
    
}

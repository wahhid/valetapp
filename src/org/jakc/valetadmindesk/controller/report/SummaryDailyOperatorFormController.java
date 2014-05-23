/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.controller.report;

import java.util.Date;
import java.util.List;
import org.jakc.valetadmindesk.db.BackingController;
import org.jakc.valetadmindesk.db.DomainController;
import org.jakc.valetadmindesk.gui.report.SummaryDailyOperatorFormFrame;
import org.jakc.valetadmindesk.vo.Auth;
import org.jakc.valetadmindesk.vo.Transvalet;
import org.openswing.swing.mdi.client.MDIFrame;

/**
 *
 * @author root
 */
public class SummaryDailyOperatorFormController {
    
    private BackingController backingController;
    private DomainController domainController;
    private SummaryDailyOperatorFormFrame frame;
    private Auth auth;
    private String filePath;
    
    public SummaryDailyOperatorFormController(Auth auth){
        this.backingController = new BackingController();
        this.domainController = new DomainController();
        this.auth = auth;
        this.frame = new SummaryDailyOperatorFormFrame(this);
        //filePath = getClass().getResource("/org/jakc/valetadmindesk/resource/report/rptdailyvaletdetailperopr.jasper").getPath();
        filePath = this.auth.getReportpath() + "/rptdailyvaletdetailperopr.jasper";  
        MDIFrame.add(this.frame);
        
    }
    
    public DomainController getDomainController() {
        return domainController;
    }
    
    
    public List<Transvalet> generateReport(Date transdate,String booth, String operator){
        List<Transvalet> os = null;
        if(booth.equals("All")){
            if(operator.equals("All")){
                os = this.backingController.getTransvaletController().getData(transdate, null, null);    
            }else{
                os = this.backingController.getTransvaletController().getData(transdate, null,operator);    
            }                    
        }else{
            if(operator.equals("All")){
                os = this.backingController.getTransvaletController().getData(transdate, booth,null);    
            }else{
                os = this.backingController.getTransvaletController().getData(transdate, booth, operator);    
            }                    
        }
        return os;
    }

    public String getFilePath() {
        return filePath;
    }

    public Auth getAuth() {
        return auth;
    }
        
    
}

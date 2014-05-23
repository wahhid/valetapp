/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.controller.report;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.engine.JasperReport;
import org.jakc.valetadmindesk.db.BackingController;
import org.jakc.valetadmindesk.db.DomainController;
import org.jakc.valetadmindesk.gui.report.DetailDailyValetFormFrame;
import org.jakc.valetadmindesk.vo.Auth;
import org.jakc.valetadmindesk.vo.Employee;
import org.jakc.valetadmindesk.vo.Transvalet;
import org.openswing.swing.mdi.client.MDIFrame;

/**
 *
 * @author root
 */
public class DetailDailyValetFormController {
    
    private BackingController backingController;
    private DomainController domainController;
    private DetailDailyValetFormFrame frame;
    private Auth auth;
    private String filePath;   
    private InputStream streamReport;
    
    public DetailDailyValetFormController(Auth auth){
        this.backingController = new BackingController();
        this.domainController = new DomainController();
        this.auth = auth;
        this.frame = new DetailDailyValetFormFrame(this);
        
        //filePath = getClass().getResource("/org/jakc/valetadmindesk/resource/report/rptdailyvaletdetail.jasper").getPath();
        //fileReport = new File(getClass().getResource("/org/jakc/valetadmindesk/resource/report/rptdailyvaletdetail.jasper").getFile());
        //streamReport =  getClass().getResourceAsStream("/org/jakc/valetadmindesk/resource/report/rptdailyvaletdetail.jasper");        
        
        filePath = this.auth.getReportpath() + "/rptdailyvaletdetail.jasper";       
        
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

    public InputStream getStreamReport() {
        return streamReport;
    }
     

    public Auth getAuth() {
        return auth;
    }
    
    
    public String getOperatorName(String operatorid){
        Employee o = this.backingController.getEmployeeController().find(operatorid);
        if(o != null){
            return o.getFullname();
        }else{
            return "";
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.controller.report;

import java.util.Date;
import java.util.List;
import org.jakc.valetadmindesk.db.BackingController;
import org.jakc.valetadmindesk.db.DomainController;
import org.jakc.valetadmindesk.gui.report.VoucheruseFormFrame;
import org.jakc.valetadmindesk.vo.Auth;
import org.jakc.valetadmindesk.vo.Transvalet;
import org.openswing.swing.mdi.client.MDIFrame;

/**
 *
 * @author root
 */
public class VoucheruseFormController {
    
    private BackingController backingController;
    private DomainController domainController;
    private VoucheruseFormFrame frame;
    private Auth auth;
    private String filePath;
    
    public VoucheruseFormController(Auth auth){
        this.backingController = new BackingController();
        this.domainController = new DomainController();
        this.auth = auth;
        this.frame = new VoucheruseFormFrame(this);
        filePath = this.auth.getReportpath() + "/rptvoucherusage.jasper";   
        MDIFrame.add(this.frame);        
    }
    
    public DomainController getDomainController() {
        return domainController;
    }
    
    
    public List<Transvalet> generateReport(Date usedate){
        List<Transvalet> os = this.backingController.getTransvaletController().getVoucherUsage(usedate);
        return os;
    }

    public String getFilePath() {
        return filePath;
    }

    public Auth getAuth() {
        return auth;
    }
    
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.controller;

import java.io.File;
import javax.persistence.EntityManagerFactory;
import org.jakc.valetadmindesk.db.BackingController;
import org.jakc.valetadmindesk.db.TransvaletController;
import org.jakc.valetadmindesk.gui.DetailPerOperatorReportFrame;
import org.jakc.valetadmindesk.db.DomainController;
import org.openswing.swing.mdi.client.MDIFrame;

/**
 *
 * @author wahhid
 */
public class DetailPerOperatorReportController {
            
    private BackingController backingController;
    private DomainController domainController;
    private DetailPerOperatorReportFrame frame;
    private String filePath;
    private File  files;
    private String title;
    
    public DetailPerOperatorReportController(){        
        this.backingController = new BackingController();
        this.domainController = new DomainController();
        this.frame = new DetailPerOperatorReportFrame(this);
        filePath = getClass().getResource("/org/jakc/valetadmindesk/resource/report/DailyReportPerBooth.jasper").getPath();
        System.out.println(this.filePath);        
        this.title = "Detail Per Operator";
        MDIFrame.add(frame);
    }

    public DomainController getDomainController() {
        return domainController;
    }

    public BackingController getBackingController() {
        return backingController;
    }


    public String getFilePath() {
        return filePath;
    }

    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.controller;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.jakc.valetadmindesk.gui.ReportViewerFrame;
import org.openswing.swing.mdi.client.MDIFrame;

/**
 *
 * @author wahhid
 */
public class ReportViewerController {
        
    private ReportViewerFrame viewerFrame;
    
   public ReportViewerController(String filePath,HashMap parameters,JRDataSource dataSource,String title){        
        System.out.println("Execute Report Viewer Controller");
        this.viewerFrame = new ReportViewerFrame(filePath,parameters,dataSource);        
        this.viewerFrame.setTitle(title);
        MDIFrame.add(viewerFrame);       
    }
   
   public ReportViewerController(InputStream streamReport,HashMap parameters,JRDataSource dataSource,String title){        
        System.out.println("Execute Report Viewer Controller");
        this.viewerFrame = new ReportViewerFrame(streamReport,parameters,dataSource);        
        this.viewerFrame.setTitle(title);
        MDIFrame.add(viewerFrame);       
    }
   
}

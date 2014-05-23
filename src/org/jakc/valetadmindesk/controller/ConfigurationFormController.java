/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.controller;

import javax.persistence.EntityManager;
import org.jakc.valetadmindesk.db.BackingController;
import org.jakc.valetadmindesk.db.DomainController;
import org.jakc.valetadmindesk.db.FormController;
import org.jakc.valetadmindesk.gui.ConfigurationFormFrame;
import org.jakc.valetadmindesk.vo.Auth;
import org.jakc.valetadmindesk.vo.Configuration;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author root
 */
public class ConfigurationFormController extends FormController {
    
    private BackingController backingController;
    private DomainController domainController;
    private ConfigurationFormFrame frame;
    private Auth auth;
    private String companyid="001";
    
    public ConfigurationFormController(Auth auth){
        this.auth = auth;
        this.backingController = new BackingController();
        this.domainController = new DomainController();
        this.frame = new ConfigurationFormFrame(this);
        MDIFrame.add(frame);
        this.frame.getForm1().setMode(Consts.READONLY);
        this.frame.getForm1().reload();        
    }
         
    public Response loadData(Class valueObjectClass){
        Configuration o;                
        o = this.backingController.getConfigurationController().getByID(companyid);                       
        if(o != null){
            return new VOResponse(o);
        }else{
            return new ErrorResponse("Data not found");            
        }                       
    }
    
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception{
        Configuration o = (Configuration) persistentObject;
        try{
            this.backingController.getConfigurationController().update(o);
            return new VOResponse(o);
        }catch(Exception ex){
            return new ErrorResponse(ex.getMessage());            
        }              
    }
    
}

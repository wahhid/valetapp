/*
* To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.controller;

import javax.swing.JFrame;
import org.jakc.valetadmindesk.ErrHandling;
import org.jakc.valetadmindesk.db.BackingController;
import org.jakc.valetadmindesk.gui.ChangePasswordDialogFrame;
import org.jakc.valetadmindesk.gui.EmployeeGridFrame;
import org.jakc.valetadmindesk.vo.Employee;
import org.openswing.swing.mdi.client.MDIFrame;

/**
 *
 * @author root
 */
public class ChangePasswordDialogController {
    
    private Employee employee;
    private ErrHandling errHandling;
    private BackingController backingController;
    private ChangePasswordDialogFrame d;
    
    
    public ChangePasswordDialogController(Employee employee){
        this.employee = employee;
        this.backingController = new BackingController();       
        d = new ChangePasswordDialogFrame(MDIFrame.getInstance(),true,this);
        d.show(true);
    }
    
    public void ChangePassword(String password){
        this.employee.setPassword(password);
        Employee o = this.backingController.getEmployeeController().update(employee);
        if(o != null){
            this.d.getLblStatus().setText("Change Successfully");
        }else{
            this.d.getLblStatus().setText("Change Failed");
        }
        
        
    }

    public Employee getEmployee() {
        return employee;
    }
    
    
    
}

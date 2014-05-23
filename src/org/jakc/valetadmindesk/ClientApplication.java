/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jakc.valetadmindesk;


import java.awt.Color;
import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.Hashtable;

import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.DefaultTreeModel;

//import nabiladbv20.createMYSQLConnection;
import org.jakc.valetadmindesk.db.ConfigurationController;
import org.jakc.valetadmindesk.db.EmployeeController;
import org.jakc.valetadmindesk.vo.Auth;
import org.jakc.valetadmindesk.vo.Configuration;
import org.jakc.valetadmindesk.vo.Employee;
import org.openswing.swing.client.SplashScreen;
import org.openswing.swing.internationalization.java.EnglishOnlyResourceFactory;
import org.openswing.swing.mdi.client.ClientFacade;
import org.openswing.swing.mdi.client.MDIController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.permissions.client.LoginController;
import org.openswing.swing.permissions.client.LoginDialog;
import org.openswing.swing.util.client.ClientSettings;
import org.openswing.swing.util.client.ClientUtils;


/**
 *
 * @author wahhid
 */
public class ClientApplication extends ClientUtils implements MDIController,LoginController {


    private AppFacade appFacade = null;
    private ClientMenu cm = new ClientMenu();
    private ClientProps cp = new ClientProps();
    protected String username=null;
    private Auth auth = new Auth();
    private Configuration configuration;

    public ClientApplication(String argv[]){
       
        ClientSettings clientSettings = new ClientSettings(
            new EnglishOnlyResourceFactory("$",cp.getProps(),true),
            new Hashtable()
        );
        
        ClientSettings.GRID_ACTIVE_CELL_BACKGROUND = new Color(205,239,255);
        ClientSettings.GRID_SELECTION_BACKGROUND = new Color(195,229,254);
        ClientSettings.FILTER_PANEL_ON_GRID=false;
        ClientSettings.ASK_BEFORE_CLOSE=true;
        ClientSettings.VIEW_BACKGROUND_SEL_COLOR = true;
        ClientSettings.VIEW_MANDATORY_SYMBOL = true;
        ClientSettings.ALLOW_OR_OPERATOR = false;
        ClientSettings.INCLUDE_IN_OPERATOR = false;
        ClientSettings.SHOW_SCROLLBARS_IN_MDI = false;
        ClientSettings.SHOW_PAGINATION_BUTTONS_ON_GRID=true;
        ClientSettings.SHOW_PAGE_NUMBER_IN_GRID=true;

        //ClientSettings.LOOK_AND_FEEL_CLASS_NAME = "com.jtattoo.plaf.mcwin.McWinLookAndFeel"; 
        ClientSettings.LOOK_AND_FEEL_CLASS_NAME = "com.jtattoo.plaf.aluminium.AluminiumLookAndFeel"; 
        

        Properties props = new Properties();

        props.put("logoString", "Valet Admin");
        props.put("backgroundColor", "232 232 232");
        String color =  "220 220 220";
        props.put("disabledBackgroundColor",color);
        props.put("systemTextFont","Arial PLAIN 11");
        props.put("controlTextFont","Arial PLAIN 11");
        props.put("menuTextFont","Arial PLAIN 11");
        props.put("userTextFont","Arial PLAIN 11");
        props.put("subTextFont","Arial PLAIN 11");
        try {
            try {
                try {
                    Class.forName(ClientSettings.LOOK_AND_FEEL_CLASS_NAME).getMethod("setCurrentTheme", new Class[]{Properties.class}).invoke(null, new Object[]{props});
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            UIManager.setLookAndFeel(ClientSettings.LOOK_AND_FEEL_CLASS_NAME);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        //MDIFrame mdi = new MDIFrame(this); 
        
        ConfigurationController configurationController = new ConfigurationController();
        this.configuration = configurationController.getByID("001");
        
        LoginDialog d = new LoginDialog(
                              null,
                              false,
                              this,
                              "Authentication - Valet Admin",
                              "Login",
                              'L',
                              "Exit",
                              'E',
                              null,
                              "DEMO38",
                              null,
                              null,
                              "EN"
                            );        
    }

    public static void main(String argv[]){
        new ClientApplication(argv);
    }
    
    @Override
    public void afterMDIcreation(MDIFrame frame) {
        
    }

    @Override
    public String getAboutImage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getAboutText() {
        return
        "Valet Admin Application\n"+
        "\n"+
        "Copyright: Copyright (C) 2011 Taman-anggrek-mall.com\n"+
        "Author: Wahyu Hidayat\n"+
        "Release: 1.0\n\n";
    }

    @Override
    public DefaultTreeModel getApplicationFunctions() {
        return cm.getMenu();
    }

    @Override
    public ClientFacade getClientFacade() {
        return this.appFacade;
    }

    @Override
    public int getExtendedState() {
        return JFrame.MAXIMIZED_BOTH;
    }

    @Override
    public ArrayList getLanguages() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getMDIFrameTitle() {
        return "Valet Admin - Taman Anggrek - " + this.auth.getEmployeename();
    }

    @Override
    public void stopApplication() {
        System.exit(0);
    }

    @Override
    public boolean viewChangeLanguageInMenuBar() {
        return false;
    }

    @Override
    public boolean viewFunctionsInMenuBar() {
        return true;
    }

    @Override
    public boolean viewFunctionsInTreePanel() {
        return true;
    }

    @Override
    public JDialog viewLoginDialog(JFrame parentFrame) {
        LoginDialog d = new LoginDialog(
                              null,
                              false,
                              this,
                              "Authentication - Valet Admin",
                              "Login",
                              'L',
                              "Exit",
                              'E',
                              null,
                              "DEMO38",
                              null,
                              null,
                              "EN"
                            );
        return d;
    }

    @Override
    public boolean viewLoginInMenuBar() {
        return true;
    }

    @Override
    public boolean viewOpenedWindowIcons() {
        return true;
    }

    @Override
    public boolean authenticateUser(Map loginInfo) throws Exception {
        boolean loginstatus=false;        
        EmployeeController employeeController = new EmployeeController();
        Employee employee = employeeController.checkLogin((String) loginInfo.get("username"),(String) loginInfo.get("password"));        
        if(employee != null){
            this.auth.setNik(employee.getNik());
            this.auth.setEmployeename(employee.getFullname());
            this.auth.setEmployeetype(employee.getEmployeetype());                   
            this.auth.setReportpath(this.configuration.getReportpath());
            this.appFacade = new AppFacade(this.auth);
            loginstatus = true;
        }
        return loginstatus;
    }

    @Override
    public int getMaxAttempts() {
        return 3;
    }

    @Override
    public void loginSuccessful(Map loginInfo) {
        MDIFrame mdi = new MDIFrame(this);
    }

    @Override
    public boolean viewFileMenu() {
        return true;
    }

    
}

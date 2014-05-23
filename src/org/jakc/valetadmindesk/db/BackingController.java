/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.db;
import javax.persistence.EntityManager;
import org.jakc.valetadmindesk.PersistenceConnection;
/**
 *
 * @author wahhid
 */
public class BackingController extends PersistenceConnection {    
            
    protected EmployeeController employeeController;
    protected BoothController boothController;
    protected PricingController pricingController;
    protected ShiftController shiftController;
    protected TransvaletController transvaletController;   
    protected IdseqController idseqController;
    protected VoucherController voucherController;
    protected VoucherdetailController voucherdetailController;
    protected VouchertypeController vouchertypeController;
    protected RolesController roleController;
    protected FormController formController;
    protected ConfigurationController configurationController;
    protected TransvaletcorrectionController transvaletcorrectionController;
    
    
    public BackingController(){        
        this.employeeController = new EmployeeController();
        this.boothController = new BoothController();
        this.pricingController = new PricingController();
        this.shiftController = new ShiftController();
        this.transvaletController = new TransvaletController();
        this.idseqController = new IdseqController();
        this.voucherController = new VoucherController();
        this.voucherdetailController = new VoucherdetailController();
        this.vouchertypeController = new VouchertypeController();       
        this.roleController = new RolesController();        
        this.formController= new FormController();
        this.configurationController = new ConfigurationController();
        this.transvaletcorrectionController = new TransvaletcorrectionController();
    }
        
    
    @Override
    public EntityManager getEntityManager(){
        return this.emf.createEntityManager();
    }

    public BoothController getBoothController() {
        return boothController;
    }

    public EmployeeController getEmployeeController() {
        return employeeController;
    }

    public IdseqController getIdseqController() {
        return idseqController;
    }

    public PricingController getPricingController() {
        return pricingController;
    }

    public ShiftController getShiftController() {
        return shiftController;
    }

    public TransvaletController getTransvaletController() {
        return transvaletController;
    }

    public VoucherController getVoucherController() {
        return voucherController;
    }

    public VoucherdetailController getVoucherdetailController() {
        return voucherdetailController;
    }

    public VouchertypeController getVouchertypeController() {
        return vouchertypeController;
    }

    public RolesController getRoleController() {
        return roleController;
    }

    public FormController getFormController() {
        return formController;
    }

    public ConfigurationController getConfigurationController() {
        return configurationController;
    }

    public TransvaletcorrectionController getTransvaletcorrectionController() {
        return transvaletcorrectionController;
    }
    
    
}

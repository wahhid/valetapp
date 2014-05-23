/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.controller;

import java.util.ArrayList;
import java.util.Map;
import javax.persistence.EntityManager;
import org.jakc.valetadmindesk.db.BackingController;
import org.jakc.valetadmindesk.db.DomainController;
import org.jakc.valetadmindesk.gui.VoucherdetailGridFrame;
import org.jakc.valetadmindesk.util.JPAUtils;
import org.jakc.valetadmindesk.vo.Voucherdetail;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author wahhid
 */
public class VoucherdetailGridController extends GridController implements GridDataLocator{

    private BackingController backingController;
    private DomainController domainController;
    private VoucherdetailGridFrame frame;
    private int voucherid;

    public VoucherdetailGridController(int voucherid){
        this.voucherid = voucherid;
        this.backingController = new BackingController();
        this.domainController = new DomainController();      
        this.frame = new VoucherdetailGridFrame(this);
        MDIFrame.add(this.frame);
    }
    
    private EntityManager getEntityManager(){
        return this.backingController.getEntityManager();
    }
    
    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        EntityManager em = this.getEntityManager();
        try{
            String strSQL = "SELECT v FROM Voucherdetail v WHERE v.voucherid=" + Integer.toString(voucherid);
            Object[] paramValues = new Object[]{};
            return JPAUtils.getBlockFromQuery(
                    action, 
                    startIndex, 
                    25, 
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    Voucherdetail.class,
                    strSQL,
                    paramValues,
                    em);            
        }catch (Throwable ex){
            ex.printStackTrace();
            return new ErrorResponse(ex.getMessage());            
        }
    }
               

    public BackingController getBackingController() {
        return backingController;
    }

    
    public DomainController getDomainController() {
        return domainController;
    }    
    
}

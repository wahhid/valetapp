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
import org.jakc.valetadmindesk.gui.TransvaletcorrectionGridFrame;
import org.jakc.valetadmindesk.vo.Transvaletcorrection;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.server.JPAUtils;

/**
 *
 * @author root
 */
public class TransvaletcorrectionGridController extends GridController implements GridDataLocator{

    private TransvaletcorrectionGridFrame frame;
    private BackingController backingController;
    private DomainController domainController;
    private String transid;
    
    public TransvaletcorrectionGridController(String transid){
        this.transid = transid;
        this.backingController = new BackingController();
        this.domainController = new DomainController();        
        this.frame = new TransvaletcorrectionGridFrame(this);
        MDIFrame.add(this.frame);
    }
    
    private EntityManager getEntityManager(){
        return this.backingController.getEntityManager();
    }
    
    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        EntityManager em = this.getEntityManager();                                 
        try{
            String strSQL = "SELECT t FROM Transvaletcorrection t WHERE t.transid = '" + transid + "'";
            Object[] paramValues = new Object[]{};
            return JPAUtils.getBlockFromQuery(
                    action, 
                    startIndex, 
                    25, 
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    Transvaletcorrection.class,
                    strSQL,
                    paramValues,
                    em); 
        }catch (Throwable ex){
            ex.printStackTrace();
            return new ErrorResponse(ex.getMessage());            
        }finally{
            
            em.close();
        } 
    }

    public BackingController getBackingController() {
        return backingController;
    }

    public DomainController getDomainController() {
        return domainController;
    }
    
    
}

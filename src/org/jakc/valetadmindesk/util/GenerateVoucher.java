/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.util;

import org.jakc.valetadmindesk.db.DomainController;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author wahhid
 */
public class GenerateVoucher {
 
    private EntityManagerFactory emf;
    private DomainController domainController;
    
    public GenerateVoucher(EntityManagerFactory emf){
        this.emf = emf;
        this.domainController = new DomainController();
    }
    public void generate(String voucherpref,int voucherdigit,int vouchernumbers){
        int i = 0;
        while (i<vouchernumbers){
            i++;
            
        }
    }
    
}

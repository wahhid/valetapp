/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jakc.valetadmindesk;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.openswing.swing.mdi.java.ApplicationFunction;
import org.openswing.swing.tree.java.OpenSwingTreeNode;

/**
 *
 * @author wahhid
 */
public class ClientMenu  {

    public ClientMenu(){

    }

    public DefaultTreeModel getMenu(){
        DefaultMutableTreeNode root = new OpenSwingTreeNode();
        DefaultTreeModel model = new DefaultTreeModel(root);

        ApplicationFunction n1 = new ApplicationFunction("Master",null);
        ApplicationFunction n11 = new ApplicationFunction("Booth","booth",null,"getBooth");
        ApplicationFunction n12 = new ApplicationFunction("Employee","employee",null,"getEmployee");
        ApplicationFunction n13 = new ApplicationFunction("Employee Type","employeetype",null,"getEmployeeType");
        ApplicationFunction n14 = new ApplicationFunction("Pricing","pricing",null,"getPricing");
        ApplicationFunction n15 = new ApplicationFunction("Shift","shift",null,"getShift");
        ApplicationFunction n16 = new ApplicationFunction("Voucher","voucher",null,"getVoucher");
        ApplicationFunction n17 = new ApplicationFunction("Configuration","configuration",null,"getConfiguration");

        ApplicationFunction n2 = new ApplicationFunction("Transaction",null);
        ApplicationFunction n21 = new ApplicationFunction("Find","find",null,"getFind");
        ApplicationFunction n22 = new ApplicationFunction("Manual and Correction","manual",null,"getManual");

        ApplicationFunction n3 = new ApplicationFunction("Report",null);
        ApplicationFunction n31 = new ApplicationFunction("Master",null);
        ApplicationFunction n311 = new ApplicationFunction("Booth","booth",null,"getBoothReport");
        ApplicationFunction n312 = new ApplicationFunction("Employee","employee",null,"getEmployeeReport");
        ApplicationFunction n313 = new ApplicationFunction("Pricing","Pricing",null,"getPricingReport");
        ApplicationFunction n314 = new ApplicationFunction("Shift","shift",null,"getShiftReport");        
        ApplicationFunction n32 = new ApplicationFunction("Transaction",null);        
        ApplicationFunction n321 = new ApplicationFunction("Detail Daily Report","dailyreport",null,"getDetailDailyReport");
        ApplicationFunction n322 = new ApplicationFunction("Per Operator Daily Report","dailyreport",null,"getPerOperatorDailyReport");        
        ApplicationFunction n323 = new ApplicationFunction("Monthly Report","monthlyreport",null,"getMonthlyReport");
        ApplicationFunction n324 = new ApplicationFunction("Correction Report","coorectionreport",null,"getCorrectionReport");
        
        ApplicationFunction n33 = new ApplicationFunction("Voucher",null); 
        ApplicationFunction n331 = new ApplicationFunction("Voucher Usage Report","voucherusage",null,"getVoucherUsage");
                
        n1.add(n11);       
        n1.add(n12);
        n1.add(n13);
        n1.add(n14);
        n1.add(n15);
        n1.add(n16);
        //n1.add(n17);

        n2.add(n21);
        n2.add(n22);

        n3.add(n31);
        n31.add(n311);
        n31.add(n312);
        n31.add(n313);
        n31.add(n314);
        n3.add(n32);
        n32.add(n321);
        n32.add(n322);
        n32.add(n323);
        n32.add(n324);
        n3.add(n33);
        n33.add(n331);
        

        root.add(n1);
        root.add(n2);
        root.add(n3);
        
        
        return model;
    }
}

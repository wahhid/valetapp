/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.eclipse.persistence.config.CacheUsage;
import org.eclipse.persistence.config.QueryHints;
import org.jakc.valetadmindesk.test.TestTransValet.TransvaletRefresh;
import org.jakc.valetadmindesk.PersistenceConnection;
import org.jakc.valetadmindesk.vo.Transvalet;

/**
 *
 * @author wahhid
 */
public class TestTransValet extends PersistenceConnection{
    
    public TestTransValet(){        
        Thread t = new Thread(new TransvaletRefresh());
        t.start();
    }
    
    public static void main(String[] argv){
        new TestTransValet();
    }
    
    
    public class TransvaletRefresh extends PersistenceConnection implements Runnable{

        @Override
        public void run() {
            while(true){
                EntityManager em = this.getEntityManager();
                String strSQL = "SELECT t FROM Transvalet t";
                Query query = em.createNamedQuery("Transvalet.findAll");
                //query.setHint("eclipselink.refresh","true");
                query.setFirstResult(0);
                query.setMaxResults(1);                    
                List<Transvalet> transvalets = query.getResultList();
                for(Transvalet transvalet: transvalets){
                    System.out.println(transvalet.getCarnumber());
                }
                em.clear();
                em.close();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TestTransValet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
}

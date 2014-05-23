/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DetailDailyValetFormFrame.java
 *
 * Created on Jan 9, 2012, 3:50:34 PM
 */
package org.jakc.valetadmindesk.gui.report;

import java.awt.Cursor;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.jakc.valetadmindesk.controller.ReportViewerController;
import org.jakc.valetadmindesk.controller.report.DetailDailyValetFormController;
import org.jakc.valetadmindesk.vo.Transvalet;
import org.openswing.swing.mdi.client.InternalFrame;

/**
 *
 * @author root
 */
public class DetailDailyValetFormFrame extends InternalFrame {

    private DetailDailyValetFormController controller;
    /** Creates new form DetailDailyValetFormFrame */
    public DetailDailyValetFormFrame(DetailDailyValetFormController controller) {
        this.controller = controller;
        initComponents();
        this.cbBooth.setDomain(this.controller.getDomainController().getCBBoothAll());
        this.cbOperator.setDomain(this.controller.getDomainController().getCBEmployeeAll());
        
    }
    
    
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        dcDate = new org.openswing.swing.client.DateControl();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbOperator = new org.openswing.swing.client.ComboBoxControl();
        cbBooth = new org.openswing.swing.client.ComboBoxControl();
        jLabel4 = new javax.swing.JLabel();

        setMaximizable(false);
        setResizable(false);
        setTitle("Detail Daily Report");
        setUniqueInstance(true);

        jToolBar1.setRollover(true);

        jButton1.setText("Generate");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Parameters"));

        dcDate.setRequired(true);

        jLabel1.setText("Date");

        jLabel3.setText("Operator");

        cbOperator.setRequired(true);

        cbBooth.setRequired(true);

        jLabel4.setText("Booth");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcDate, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbBooth, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(cbOperator, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
                .addGap(352, 352, 352))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dcDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbBooth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbOperator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:    
    HashMap parameters = new HashMap();
    parameters.put("BOOTH_NAME", this.cbBooth.getValueAt(this.cbBooth.getSelectedIndex()));
    parameters.put("PRINTED_BY", this.controller.getAuth().getEmployeename());    
    parameters.put("OPERATOR_ID", this.cbOperator.getValue());
    parameters.put("OPERATOR_NAME", "");    
    parameters.put("REPORT_FOR_DATE", new SimpleDateFormat("dd-MM-yyyy").format(this.dcDate.getDate()));
    Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
    this.setCursor(hourglassCursor);    
    List<Transvalet> os = this.controller.generateReport(dcDate.getDate(),this.cbBooth.getValue().toString(), this.cbOperator.getValue().toString());
    JRDataSource dataSource = new JRBeanCollectionDataSource(os);                            
    Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
    this.setCursor(normalCursor);        
    new ReportViewerController(this.controller.getFilePath(),parameters,dataSource,"");      
           
}//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.ComboBoxControl cbBooth;
    private org.openswing.swing.client.ComboBoxControl cbOperator;
    private org.openswing.swing.client.DateControl dcDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}

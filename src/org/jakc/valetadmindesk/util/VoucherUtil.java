/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.util;

/**
 *
 * @author root
 */
public class VoucherUtil {
 
    public String getVoucherDetailID(String voucherpref,int voucherdigit, int voucherseq){
        String voucherdetailid="";
        int voucherpreflength = voucherpref.length();
        String strvoucherseq = Integer.toString(voucherseq);
        int strvoucherseqlength = strvoucherseq.length();
        int zeroDigit = voucherdigit - voucherpreflength - strvoucherseqlength;
        String strZeroDigit = "";
        for(int i=0;i<zeroDigit;i++){
            strZeroDigit = strZeroDigit + "0";
        }
        voucherdetailid = voucherpref + strZeroDigit + strvoucherseq;
        return voucherdetailid;
    }
    
}

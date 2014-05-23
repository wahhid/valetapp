/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.vo;

/**
 *
 * @author wahhid
 */
public class Auth {
 
    private String nik;
    private String employeename;
    private String employeetype;
    private String reportpath;
            
    public Auth(){
                
    }

   

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }


    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public String getEmployeetype() {
        return employeetype;
    }

    public void setEmployeetype(String employeetype) {
        this.employeetype = employeetype;
    }

    public String getReportpath() {
        return reportpath;
    }

    public void setReportpath(String reportpath) {
        this.reportpath = reportpath;
    }
    
}

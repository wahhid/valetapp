/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.valetadmindesk.services;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jakc.valetadmindesk.db.ShiftController;
import org.jakc.valetadmindesk.vo.Shift;
import org.jakc.valetadmindesk.vo.Transvalet;

/**
 *
 * @author wahhid
 */
public class FindShift {
                
    private SimpleDateFormat fullFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private ShiftController controller;
    private Date transdate;    
    private Date startdate;
    private Date enddate;
    
    public FindShift(){
        this.controller = new ShiftController();
    }
    
    public Shift Find(){
        List<Shift> shifts;
        Shift currentShift = new Shift();
        shifts = this.controller.getAll();
        if(shifts != null){
            for(Shift shift : shifts){
                Date currentDate = enddate;                
                String strCurrentDate = dateFormat.format(currentDate);
                if(shift.getNextday() == false){
                    try {
                        //Same Day
                        Date startTime = fullFormat.parse(strCurrentDate + " " + timeFormat.format(shift.getStarttime()));
                        Date endTime = fullFormat.parse(strCurrentDate + " " + timeFormat.format(shift.getEndtime()));
                        if(startTime.before(currentDate) && endTime.after(currentDate)){
                            currentShift = shift;
                            transdate = currentDate;
                            break;
                        }
                    } catch (ParseException ex) {
                        System.out.println();
                    }                                        
                }else{
                    //Next Day                    
                    Date startTime;
                    Date endTime;
                    try {                        
                        startTime = fullFormat.parse(strCurrentDate + " 00:00:00");                        
                        endTime = fullFormat.parse(strCurrentDate + " 07:00:00");
                        if(startTime.before(currentDate) && endTime.after(currentDate)){
                            currentShift = shift;                           
                            Calendar cal1 = Calendar.getInstance();                                
                            cal1.setTime(currentDate);
                            cal1.add(Calendar.DATE, -1);
                            transdate = cal1.getTime();                            
                        }else{
                            
                            startTime = fullFormat.parse(strCurrentDate + " 22:00:00");
                            endTime = fullFormat.parse(strCurrentDate + " 23:59:99");
                            if(startTime.before(currentDate) && endTime.after(currentDate)){
                                currentShift = shift;
                                transdate = currentDate;                                                           
                            }
                        }                        
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }                                        
                }
            }
        }else{
            currentShift = null;
        }        
        return currentShift;
    }

    public Date getTransdate() {
        return transdate;
    }

    public void setTransdate(Date transdate) {
        this.transdate = transdate;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
    
    
}

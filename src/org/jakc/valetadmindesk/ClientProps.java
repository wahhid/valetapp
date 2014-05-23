/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jakc.valetadmindesk;

import java.util.Properties;

/**
 *
 * @author wahhid
 */
public class ClientProps {


    public ClientProps(){

    }

    public Properties getProps(){
        Properties props = new Properties();

        //Common Properties
        props.setProperty("status", "Status");
        props.setProperty("createddate", "Created Date");
        props.setProperty("createdby", "Created By");
        props.setProperty("updateddate", "Updated Date");
        props.setProperty("updatedby", "Updated By");
        
        //Shift Properties
        props.setProperty("boothid", "Id");
        props.setProperty("boothname", "Name");
        props.setProperty("printin", "Print In");
        props.setProperty("printout", "Print Out");
        

        //Part Properties
        props.setProperty("partno", "Part No");
        props.setProperty("partname", "Name");
        props.setProperty("boxno", "Box");
        props.setProperty("qty", "Quantity");
        props.setProperty("qtyeach", "Each");
        props.setProperty("duration", "Duration");
        props.setProperty("qrcode", "QR Barcode");
        props.setProperty("customerid", "Customer");
        props.setProperty("customerpartno", "Unique No");

        //Transvalet Properties
        props.setProperty("transid", "Trans ID");
        props.setProperty("transdate", "Date");
        props.setProperty("transtime", "Time");

        //Shift
        props.setProperty("shiftid", "Shift ID");
        props.setProperty("shiftname", "Shift Name");


        //Route Detail
        props.setProperty("routedetailid", "Reader Detail ID");
        props.setProperty("routeorder", "Reader Order");

        //transdetail
        props.setProperty("transdetailid", "Trans Detail ID");
        props.setProperty("transdetaildate", "Trans Detail Date");


        //Configuration Paramater
        props.setProperty("confparamname", "Name");
        props.setProperty("confparamvalue", "Value");


        //Notification
        props.setProperty("notiname", "Name");
        props.setProperty("soundfile", "Sound");
        props.setProperty("repeatsound", "Repeatation");
        props.setProperty("notistatus", "Status");


        return props;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author USER
 */
public class Test {
    
    public static void main(String[] args){
                

        Date date = new Date();
        Calendar c = Calendar.getInstance();
        System.out.println(date);
        System.out.println(c.getTime());
        System.out.println(c.getTime().getTime());

        System.out.println(TimeZone.getDefault().getRawOffset());

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        System.out.println(c.getTime());
        System.out.println(c.getTime().getTime());
               
        System.out.println(TimeZone.getDefault().getOffset(c.getTime().getTime()));
  
        
    }
}

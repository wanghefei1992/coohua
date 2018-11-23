package com.coohua;

import com.sun.jna.platform.win32.WinNT;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.omg.CORBA.INTERNAL;

import java.io.File;
import java.util.Calendar;

public class CommonLib {
    public static void waitTime(int p_time){

        try {
            Thread.sleep(p_time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static String getCurrentTime(){
        Calendar ca = Calendar.getInstance();
        int year=ca.get(Calendar.YEAR);
        int month=ca.get(Calendar.MONTH);
        int day=ca.get(Calendar.DATE);
        int hour=ca.get(Calendar.HOUR);
        int minute=ca.get(Calendar.MINUTE);
        int second=ca.get(Calendar.SECOND);
        String currentTime=(String.valueOf(year) + "-" + String.valueOf(month + 1) + "-"
                + String.valueOf(day) + "-" + String.valueOf(hour) + "-"
                + String.valueOf(minute) + "-" + String.valueOf(second));
        return currentTime;
    }
    public static String iniFileRead(String p_file,String p_section,String p_key) {
        File file = new File(p_file);
        if (file.exists()) {
            HierarchicalINIConfiguration IniConfig = null;
            try {
                IniConfig = new HierarchicalINIConfiguration(file);
            } catch (ConfigurationException e) {
                e.printStackTrace();
            }
            IniConfig.getString(p_section + "." + p_key);

        }
        return null;
    }

}

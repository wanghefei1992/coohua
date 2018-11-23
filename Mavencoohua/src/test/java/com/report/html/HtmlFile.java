package com.report.html;

import com.report.base.IFile;
import java.io.PrintStream;
import java.util.logging.FileHandler;
import java.io.IOException;
import java.util.logging.Logger;

 public  class HtmlFile implements IFile {
    private Logger logger = Logger.getLogger(HtmlFile.class.getName());
    private FileHandler fileHTML;

    public void createLog(String p_logName, boolean p_apped)
    {
        try
        {
            this.fileHTML = new FileHandler(p_logName, p_apped);
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.fileHTML.setFormatter(new HtmlReportFormatter());
        this.logger.addHandler(this.fileHTML);
    }

    public void createLog(String p_logName)
    {
        try {
            this.fileHTML = new FileHandler(p_logName);
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.fileHTML.setFormatter(new HtmlReportFormatter());
        this.logger.addHandler(this.fileHTML);
    }

    public void closeLog()
    {
        this.fileHTML.close();
        RecordStore.p_pass = 0;
        RecordStore.p_fail = 0;
        RecordStore.result = "";
        RecordStore.expected = "";
        RecordStore.actual = "";
    }

    public void write(String p_info, String p_expected, String p_actual)
    {
        RecordStore.actual = p_actual;
        RecordStore.expected = p_expected;

        if (p_expected.equals(p_actual))
            RecordStore.result = "Pass";
        else
            RecordStore.result = "Fail";
        try
        {
            this.logger.info(p_info);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" logger write exception!");
        }
    }

     public void write(String p_info1, Object p_expected, Object p_actual) {
         RecordStore.actual = p_actual;
         RecordStore.expected = p_expected;

         if (p_expected.equals(p_actual))
             RecordStore.result = "Pass";
         else
             RecordStore.result = "Fail";
         try
         {
             this.logger.info(p_info1);
         } catch (Exception e) {
             e.printStackTrace();
             System.out.println(" logger write exception!");
         }
     }
    public void write(String p_info)
    {
        this.logger.info(p_info);
    }

    public void write(String p_info, String p_result)
    {
        RecordStore.result = p_result;
        try
        {
            this.logger.info(p_info);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" logger write exception!");
        }
    }

    public String read()
    {
        return null;
    }

    public String read(String p_key)
    {
        return null;
    }

    public String read(String p_section, String p_key)
    {
        return null;
    }

     public String read(String P_info1, String P_info2, String p_info3) {
         return null;
     }

 }

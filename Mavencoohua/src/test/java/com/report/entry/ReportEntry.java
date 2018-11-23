package com.report.entry;

import com.report.base.IFile;
import com.report.html.HtmlFile;
import com.report.ini.IniFile;
import com.report.log.LogFile;
//import com.report.properties.PropertiesFile;
import com.report.properties.PropertiesFile;
import com.report.txt.TxtFile;

public class ReportEntry {

    IFile myFile;
    String fileName;

    public void crateLog(String p_file)
    {
        if (p_file.endsWith(".ini"))
             this.myFile = new IniFile();
        else if (p_file.endsWith(".properties"))
             myFile = new PropertiesFile();
        else if (p_file.endsWith(".html"))
             myFile = new HtmlFile();
        else if (p_file.endsWith(".txt"))
            myFile = new TxtFile();
        else if (p_file.endsWith(".log"))
             myFile = new LogFile();
        else {
            myFile = null;
        }
        if (this.myFile != null) {
            myFile.createLog(p_file);
        }
        else
        {
            System.out.println("文件类型不存在");
            System.exit(0);
        }

    }

    public void closeLog() {
        this.myFile.closeLog();
    }

    public void write(String p_info) {
        this.myFile.write(p_info);
    }

    public void write(String p_info1, String p_info2)
    {
        this.myFile.write(p_info1, p_info2);
    }

    public void write(String p_info1,Object p_info2,Object p_info3) {

        this.myFile.write(p_info1,p_info2,p_info3);
    }


    public String read(String p_key) {
        return this.myFile.read(p_key).toString();
    }

    public String read(String p_section, String p_key) {
        return this.myFile.read(p_section, p_key);
    }
}

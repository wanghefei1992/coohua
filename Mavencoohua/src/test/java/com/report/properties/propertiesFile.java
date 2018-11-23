package com.report.properties;

import com.report.base.IFile;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;
import java.io.IOException;

public class  propertiesFile implements IFile {
    File file = null;
    String filePath =null;
    PropertiesConfiguration properties = new PropertiesConfiguration();

    public void createLog (String p_filePath) {
        this.filePath = p_filePath;
        this.file = new File(this.filePath);
       if (!file.exists()){
           try {
               this.file.createNewFile();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
        try {
            this.properties.load(new File(this.filePath));
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public String read(String p_key) {
        return   this.properties.getProperty(this.filePath).toString();

    }

    public void closeLog() {
        try {
            this.properties.save(new File(this.filePath));
        } catch (ConfigurationException e1) {
            e1.printStackTrace();
        }
        this.properties=null;
        this.filePath=null;
    }

    public void write(String p_key, String p_value) {
        this.properties.setProperty(p_key,p_value);

    }

    public void write(String p_info) {

    }

    public void write(String P_info1, Object P_info2, Object p_info3) {

    }

    public String read(String P_info1, String P_info2, String p_info3) {
        return null;
    }

    public void write(String P_info1, String P_info2, String p_info3) {

    }

    public String read(String P_info1, String p_info2) {
        return null;
    }
}

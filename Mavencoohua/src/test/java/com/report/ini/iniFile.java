package com.report.ini;

import com.report.base.IFile;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;

import java.io.File;
import java.io.IOException;

public class iniFile implements IFile {
    private String fileName = null;
    HierarchicalINIConfiguration ini = null;
//创建log
    public void createLog(String p_fileName) {
        this.fileName = p_fileName;
        File file = new File(this.fileName);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            this.ini = new HierarchicalINIConfiguration(this.fileName);
            this.ini.load(new File(this.fileName));
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
//向配置文件写入信息
    public void write(String section, String key, String value) {
        this.ini.setProperty(section + "." +key,value);

    }
//读取指定ini配置文件信息
    public String read(String section, String key) {
        return this.ini.getString(section + "." + key);
    }

    public void closeLog() {
        try {
            this.ini.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        this.fileName = null;
        this.ini = null;
    }

    public String read() {
        return null;
    }
    //读取自定义ini配置文件信息
    public String read(String p_file, String p_section, String p_key) {
        try {
            this.ini = new HierarchicalINIConfiguration(p_file);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return ini.getString(p_section+"."+p_key);


    }

    public void write(String p_info) {

    }

    public void write(String P_info1, String p_info2) {

    }

    public void write(String P_info1, Object P_info2, Object p_info3) {

    }

    public String read(String p_info1) {
        return null;
    }
}

package com.coohua;

public interface IFile {
    public abstract void write(String p_info);
    public abstract void createLog(String p_info);
    public abstract void closeLog();
    public abstract void write(String P_info1,String p_info2);
    public abstract void write (String P_info1,String P_info2,String p_info3);
    public abstract void write(String P_info1,Object P_info2,Object p_info3);
    public abstract  String read();
    public abstract String read(String P_info1,String p_info2);
    public abstract String read(String P_info1,String P_info2,String p_info3);
}

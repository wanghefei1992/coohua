package com.coohua;
//与外部文件的一个接口可将一些参数放进配置项中
public class DataStore {
         public static String D_IniConfig  ="./config/config.ini";
         public static String D_ReportPath =CommonLib.iniFileRead(D_IniConfig,"Report", "Path");
         public static String D_LogPath =CommonLib.iniFileRead(D_IniConfig,"Log", "Path");



}

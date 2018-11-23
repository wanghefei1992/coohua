package com.report.html;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public class FileUtil {
    public FileUtil() {
    }

    public static void copyFile(String p_srcFile, String p_destDir) {
        try {
            FileUtils.copyFileToDirectory(new File(p_srcFile), new File(p_destDir));
            System.out.println("copy file : " + p_srcFile + " to folder: " + p_destDir);
        } catch (IOException var3) {
            System.err.println("Excepion in method copyFile(String p_srcFile,String p_destDir),details info is:" + var3.getMessage());
        }

    }

    public static void deleteFile(String p_file) {
        FileUtils.deleteQuietly(new File(p_file));
        System.out.println("delete file : " + p_file);
    }

    public static File[] getSpecifalFile(String p_fold, String p_regular) {
        File dir = new File(p_fold);
        FileFilter fileFilter = new WildcardFileFilter(p_regular);
        return dir.listFiles(fileFilter);
    }
}

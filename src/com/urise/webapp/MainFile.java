package com.urise.webapp;

import java.io.File;

public class MainFile {
//    D:\basejava
    public static void main(String[] args) {
        File directory = new File("D:\\basejava");
        recursionWalk(directory);
    }
    private static void recursionWalk(File dir) {
        if (dir.isDirectory()) {
            System.out.println("\n" + dir);
            File[] filesList = dir.listFiles();
            if (filesList != null) {
                for (File fileName : filesList) {
                    if (fileName.isDirectory())
                        recursionWalk(fileName);
                    else
                        System.out.println(fileName);
                }
            }
        }
    }
}

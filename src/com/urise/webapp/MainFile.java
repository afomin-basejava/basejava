package com.urise.webapp;

import java.io.File;

public class MainFile {
    public static final int INDENT = 2;
    private static final File FILE = new File("\\basejava");
    public static void main(String[] args) {
        printDirectoryDeeply(FILE, INDENT);
    }
    private static void printDirectoryDeeply(File dir, int indent) { // indent < 0 === indent = 0
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("File " + dir + " must be a FILE DIRECTORY");
        }
        if (dir.isDirectory()) {
            indentPrint(dir, indent);
            File[] filesList = dir.listFiles();
            if (filesList != null) {
                for (File fileName : filesList) {
                    if (fileName.isDirectory()) {
                        printDirectoryDeeply(fileName, indent);
                    } else {
                        indentPrint(fileName, indent);
                    }
                }
            }
        }
    }

    private static void indentPrint(File file, int indent) { // indent < 0 === indent = 0
        int size = file.toPath().getNameCount();
        for (int i = 0; i < indent * (size - FILE.toPath().getNameCount()); i++) {
            System.out.print(" ");
        }
        System.out.println(file.getName());
    }
}

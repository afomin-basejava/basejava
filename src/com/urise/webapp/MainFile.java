package com.urise.webapp;

import java.io.File;

public class MainFile {
    private static final int INDENT = 2;
    private static final File FILE = new File("..\\basejava\\");
    private static final String SHIFT;
    static {
        StringBuilder sb = new StringBuilder(INDENT);
        for (int i = 0; i < INDENT; i++) {
            sb.append(" ");
        }
        SHIFT = sb.toString();
    }
    public static void main(String[] args) {
        if (FILE.isDirectory()) {
            printDirectoryDeeply(FILE, "");
        } else {
            throw new IllegalArgumentException(FILE + ": must be a directory!");
        }
    }

    private static void printDirectoryDeeply(File dir, String initialShift) {
        if (dir.isDirectory()) {
            System.out.println(initialShift + dir.getName());
            File[] filesList = dir.listFiles();
            if (filesList != null) {
                for (File fileName : filesList) {
                    if (fileName.isDirectory()) {
                        printDirectoryDeeply(fileName, initialShift + SHIFT);
                    } else {
                        System.out.println(initialShift + SHIFT + fileName.getName());
                    }
                }
            }
        }
    }

    private static void printDirectoryDeeply(File dir, int indent) {
        if (dir.isDirectory()) {
            printFileNameWithIndent(dir, indent);
            File[] filesList = dir.listFiles();
            if (filesList != null) {
                for (File fileName : filesList) {
                    if (fileName.isDirectory()) {
                        printDirectoryDeeply(fileName, indent);
                    } else {
                        printFileNameWithIndent(fileName, indent);
                    }
                }
            }
        }
    }

    private static void printFileNameWithIndent(File file, int indent) { // indent < 0 === indent = 0
        int size = file.toPath().getNameCount();
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < indent * (size - FILE.toPath().getNameCount()); i++) {
            sb.append(" ");
        }
        System.out.println(sb.toString() + file.getName());
    }
}

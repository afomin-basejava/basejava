package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.*;

import java.io.BufferedReader;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Interactive test for com.urise.webapp.storage.ArrayStorage implementation
 * (just run, no need to understand)
 */
public class MainArray {
    private  static final Storage ARRAY_STORAGE;
    static {
        System.out.println("Type 1 - ArrayStorage" + "\n" +
                "2 - SortedArrayStorage" + "\n" +
                "3 - ListStorage" + "\n" +
                "4 - MapStorage" + "\n" +
                "5 - MapStorageResume" + "\n");
//        String accessType = new Scanner(new BufferedInputStream(System.in){public void close(){}}).nextLine();
        int accessType = new Scanner(new FilterInputStream(System.in){public void close(){}}).nextInt();
        if (accessType == 1) {
            ARRAY_STORAGE = new ArrayStorage();
            System.out.println("accessType = " + accessType + " - " + ARRAY_STORAGE.getClass().getName());
        } else if (accessType == 2) {
            ARRAY_STORAGE = new SortedArrayStorage();
            System.out.println("accessType = " + accessType + " - " + ARRAY_STORAGE.getClass().getName());
        } else if (accessType == 3) {
            ARRAY_STORAGE = new ListStorage();
            System.out.println("accessType = " + accessType + " - " + ARRAY_STORAGE.getClass().getName());
        } else if (accessType == 4) {
            ARRAY_STORAGE = new MapStorage();
            System.out.println("accessType = " + accessType + " - " + ARRAY_STORAGE.getClass().getName());
        } else if (accessType == 5) {
            ARRAY_STORAGE = new MapStorageResume();
            System.out.println("accessType = " + accessType + " - " + ARRAY_STORAGE.getClass().getName());
        } else {
            ARRAY_STORAGE = new ArrayStorage();
            System.out.println("Default accessType = " + ARRAY_STORAGE.getClass().getName());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Resume resume;
        while (true) {
            System.out.print("Введите одну из команд - (list | save fullName | delete uuid | get uuid | update uuid fullName | clear | exit): ");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 3) {
                System.out.println("Неверная команда.");
                continue;
            }
            String param = null;
            if (params.length > 1) {
                param = params[1].intern();
            }
            switch (params[0]) {
                case "list":
                    printAll();
                    break;
                case "size":
                    System.out.println(ARRAY_STORAGE.size());
                    break;
                case "save":
                    Random random = new Random();
//                    ARRAY_STORAGE.save(new Resume(param, "FullName" + random.nextInt(100)));
                    ARRAY_STORAGE.save(new Resume(param));
                    printAll();
                    break;
                case "update":
//                    resume = new Resume(param, "FullName" + new Random().nextInt(1000));
                    resume = new Resume(param, params[2]);
                    ARRAY_STORAGE.update(resume);
                    printAll();
                    break;
                case "delete":
                    ARRAY_STORAGE.delete(param);
                    printAll();
                    break;
                case "get":
                    System.out.println(ARRAY_STORAGE.get(param));
                    break;
                case "clear":
                    ARRAY_STORAGE.clear();
                    printAll();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Неверная команда.");
                    break;
            }
        }
    }


    static void printAll() {
        List<Resume> all = ARRAY_STORAGE.getAllSorted();
        System.out.println("----------------------------");
        if (all.size() == 0) {
            System.out.println("Empty");
        } else {
            for (Resume resume : all) {
                System.out.println(resume);
            }
        }
        System.out.println("----------------------------");
    }
}
package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ArrayStorage;
import com.urise.webapp.storage.SortedArrayStorage;
import com.urise.webapp.storage.Storage;

import java.io.BufferedReader;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Interactive test for com.urise.webapp.storage.ArrayStorage implementation
 * (just run, no need to understand)
 */
public class MainArray {
    private  static final Storage ARRAY_STORAGE;
    static {
        System.out.println("Type 1 or 2:");
//        String accessType = new Scanner(new BufferedInputStream(System.in){public void close(){}}).nextLine();
        int accessType = new Scanner(new FilterInputStream(System.in){public void close(){}}).nextInt();
        if (accessType == 1) {
            ARRAY_STORAGE = new ArrayStorage();
            System.out.println("accessType = " + accessType + " - " + ARRAY_STORAGE.getClass().getName());
        }
        else {
            ARRAY_STORAGE = new SortedArrayStorage();
            System.out.println("accessType = " + accessType + " - " + ARRAY_STORAGE.getClass().getName());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Resume r;
        while (true) {
            System.out.print("Введите одну из команд - (list | save uuid | delete uuid | get uuid | clear | exit): ");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 2) {
                System.out.println("Неверная команда.");
                continue;
            }
            String uuid = null;
            if (params.length == 2) {
                uuid = params[1].intern();
            }
            switch (params[0]) {
                case "list":
                    printAll();
                    break;
                case "size":
                    System.out.println(ARRAY_STORAGE.size());
                    break;
                case "save":
                    r = new Resume();
                    r.setUuid(uuid);
                    ARRAY_STORAGE.save(r);
                    printAll();
                    break;
                case "update":
                    r = new Resume();
                    r.setUuid(uuid);
                    ARRAY_STORAGE.update(r);
                    printAll();
                    break;
                case "delete":
                    r = new Resume();
                    r.setUuid(uuid);
                    ARRAY_STORAGE.delete(r);
                    printAll();
                    break;
                case "get":
                    r = new Resume();
                    r.setUuid(uuid);
                    System.out.println(ARRAY_STORAGE.get(r));
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
        Resume[] all = ARRAY_STORAGE.getAll();
        System.out.println("----------------------------");
        if (all.length == 0) {
            System.out.println("Empty");
        } else {
            for (Resume resume : all) {
                System.out.println(resume);
            }
        }
        System.out.println("----------------------------");
    }
}
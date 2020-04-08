package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SortedArrayStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainSortedArray {
    private final static SortedArrayStorage SORTED_ARRAY_STORAGE = new SortedArrayStorage();

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
                    System.out.println(SORTED_ARRAY_STORAGE.size());
                    break;
                case "save":
                    r = new Resume();
                    r.setUuid(uuid);
                    SORTED_ARRAY_STORAGE.save(r);
                    printAll();
                    break;
                case "update":
                    r = new Resume();
                    r.setUuid(uuid);
                    SORTED_ARRAY_STORAGE.update(r);
                    printAll();
                    break;
                case "delete":
                    r = new Resume();
                    r.setUuid(uuid);
                    SORTED_ARRAY_STORAGE.delete(r);
                    printAll();
                    break;
                case "get":
                    r = new Resume();
                    r.setUuid(uuid);
                    System.out.println(SORTED_ARRAY_STORAGE.get(r));
                    break;
                case "clear":
                    SORTED_ARRAY_STORAGE.clear();
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
        Resume[] all = SORTED_ARRAY_STORAGE.getAll();
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

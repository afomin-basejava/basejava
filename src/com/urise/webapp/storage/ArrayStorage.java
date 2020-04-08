package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int CAPACITY = 4;
    private static int size = 0;
    private Resume[] storage = new Resume[CAPACITY];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size < CAPACITY) {
            if (indexOf(resume) < 0) {
                storage[size++] = resume;
            } else {
                System.out.println("Resume " + resume.getUuid() + " duplicate doesn't allowed!");
            }
        } else {
            System.out.println("Resume: save attempt -" + resume + "- out of storage memory limit!");
        }
    }

    public Resume get(Resume resume) {
        int index = indexOf(resume);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("Resume " + resume.getUuid() + " doesn't exist!");
        return null;
    }

    public void delete(Resume resume) {
        int index = indexOf(resume);
        if (index >= 0) {
            if (size - 1 - index >= 0) {
                System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            }
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume " + resume.getUuid() + " doesn't exist - deletion impossible!");
        }
    }

    public void update(Resume resume) {
        int index = indexOf(resume);
        if (index >= 0) {
            if (storage[index].getUuid().equals(resume.getUuid())) {
                storage[index] = resume;
            }
        } else {
            System.out.println("Resume " + resume.getUuid() + " doesn't exist - update impossible!");
        }
    }

    private int indexOf(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(resume.getUuid())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}

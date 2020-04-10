package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage{
    protected static final int CAPACITY = 4;
    protected static int size = 0;
    protected final Resume[] storage = new Resume[CAPACITY];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size < CAPACITY) {
            int index = indexOf(resume);
            if (index >= 0) {
                System.out.println("Resume " + resume.getUuid() + " duplicate doesn't allowed!");
            } else {
                saveResume(resume, index);
                size++;
            }
        } else {
            System.out.println("Resume: save attempt -" + resume + "- out of storage memory limit!");
        }
    }

    public Resume get(Resume resume) {
        int index = indexOf(resume);
        if (index < 0) {
            System.out.println("Resume " + resume.getUuid() + " doesn't exist!");
            return null;
        }
        return storage[index];
    }

    public void delete(Resume resume) {
        int index = indexOf(resume);
        if (index >= 0) {
            if (size - 1 - index >= 0) {
                deleteResume(resume, index);
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
                storage[index] = resume;
        } else {
            System.out.println("Resume " + resume.getUuid() + " doesn't exist - update impossible!");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public abstract int indexOf(Resume resume);

    protected abstract void saveResume(Resume resume, int insertPoint);

    protected abstract void deleteResume(Resume resume, int insertPoint);

}

package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage{
    protected static final int CAPACITY = 4;
    protected static int size = 0;
    protected Resume[] storage = new Resume[CAPACITY];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public abstract void save(Resume resume);

    public abstract Resume get(Resume resume);

    public abstract void delete(Resume resume);

    public abstract void update(Resume resume);

    public abstract int indexOf(Resume resume);

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

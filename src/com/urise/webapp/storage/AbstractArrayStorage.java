package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
//    protected static final int ARRAY_CAPACITY = 5;//Integer.MAX_VALUE;

    protected final Resume[] storage = new Resume[CAPACITY];

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

//    public abstract int indexOf(Resume resume);

    @Override
    protected void clearStorage() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected Resume getResume(int index) {
        return storage[index];
    }

    @Override
    protected void fillZeroLastElement() {
        storage[size - 1] = null;
    }

    @Override
    protected void updateResume(Resume resume, int index) {
        storage[index] = resume;
    }

}

package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage{
    protected static final int CAPACITY = 4;
    protected static int size = 0;
    protected final Resume[] storage = new Resume[CAPACITY];

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected void updateResume(Resume resume, Object index) {
        storage[(int) index] = resume;
    }

    @Override
    protected Resume getResume(Object index) {
        return storage[(int) index];
    }

    protected void checkStorageOverflow(Resume resume) {
        if (size == CAPACITY) {
            throw new StorageException("Storage overflow to save resume", resume.getUuid());
        }
    }

    protected void storeResumeByindex(Resume resume, int index) {
        storage[index] = resume;
        size++;
    }

    protected void resize() {
        storage[size - 1] = null;
        size--;
    }

}

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

    @Override
    protected void saveResume(Resume resume, Object index) {
        if (size == CAPACITY) {
            throw new StorageException("Storage overflow to save resume", resume.getUuid());
        }
        addResumeToStorage(resume, (int) index);
        size++;
    }

    @Override
    protected void deleteResume(Object index) {
        deleteResumeFromStorage((int) index);
        storage[size - 1] = null;
        size--;
    }

    abstract protected void addResumeToStorage(Resume resume, int index);

    abstract protected void deleteResumeFromStorage(int index);

    @Override
    protected boolean isExistResume(Object index) {
        return ((Integer) index) >= 0;
    }

}

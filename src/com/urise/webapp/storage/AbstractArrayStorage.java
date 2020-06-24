package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer>{
    protected static final int CAPACITY = 5;  // >= 2 : see fillStorage() AbstractStorageTest
    protected static int size = 0;
    protected final Resume[] storage = new Resume[CAPACITY];

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected void updateResume(Resume resume, Integer index) {
        storage[index] = resume;
    }

    @Override
    protected Resume getResume(Integer index) {
        return storage[index];
    }

    @Override
    protected void saveResume(Resume resume, Integer index) {
        if (size == CAPACITY) {
            throw new StorageException("Storage overflow to save resume", resume.getUuid());
        }
        addResumeToStorage(resume, index);
        size++;
    }

    @Override
    protected void deleteResume(Integer index) {
        deleteResumeFromStorage(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected List<Resume> getAll() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    protected abstract void addResumeToStorage(Resume resume, int index);

    protected abstract void deleteResumeFromStorage(int index);

    @Override
    protected boolean isExistResume(Integer index) {
        return (index) >= 0;
    }

}

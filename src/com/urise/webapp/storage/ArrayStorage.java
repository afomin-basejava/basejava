package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void saveResume(Resume resume, Object index) {
        checkStorageOverflow(resume);
        storage[size] = resume;
        size++;
    }

    @Override
    protected void deleteResume(Object index) {
        storage[(Integer) index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

}

package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public int indexOf(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(resume.getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertResumeIntoStorage(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected void deleteResumeFromStorage(Resume resume, int index) {
        storage[index] = storage[size - 1];
    }
}

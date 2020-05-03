package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import static java.util.Arrays.binarySearch;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return binarySearch(storage, 0, size(), searchKey);
    }

    @Override
    protected void saveResume(Resume resume, int index) {
        if (size == CAPACITY) {
            throw new StorageException("Storage overflow to save resume", resume.getUuid());
        }
        int insertPoint = -index - 1;
        System.arraycopy(storage, insertPoint, storage, insertPoint + 1,size() - insertPoint);
        storage[insertPoint] = resume;
        size++;
    }

    @Override
    protected void deleteResume(String uuid, int index) {
//        if (size - 1 - index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            storage[size - 1] = null;
            size--;
//        }
    }

}

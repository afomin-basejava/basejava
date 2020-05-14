package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import static java.util.Arrays.binarySearch;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return binarySearch(storage, 0, size(), searchKey);
    }

    @Override
    protected boolean isExistResume(Object index) {
        return ((Integer) index) >= 0;
    }

    @Override
    protected void saveResume(Resume resume, Object indeX) {
        checkStorageOverflow(resume);
        int index = (Integer) indeX;
        int insertPoint = -index - 1;
        System.arraycopy(storage, insertPoint, storage, insertPoint + 1,size() - insertPoint);
        storage[insertPoint] = resume;
        size++;
    }

    @Override
    protected void deleteResume(Object indeX) {
//        if (size - 1 - index >= 0) {
        int index = (Integer) indeX;
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        storage[size - 1] = null;
        size--;
//        }
    }

}

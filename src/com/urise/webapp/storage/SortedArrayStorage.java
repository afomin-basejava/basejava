package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import static java.util.Arrays.binarySearch;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public int indexOf(Resume resume) {
        int insertPoint = binarySearch(storage, 0, size(), resume);
//        if (insertPoint < 0) {
//            insertPoint = -insertPoint - 1;
//        }
        return insertPoint;
    }

    @Override
    protected void insertResumeIntoStorage(Resume[] storage, Resume resume, int index) {
        int insertPoint = -index - 1;
        System.arraycopy(storage, insertPoint, storage, insertPoint + 1,size() - insertPoint);
        storage[insertPoint] = resume;
    }
}

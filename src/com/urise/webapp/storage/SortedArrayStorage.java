package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import static java.util.Arrays.binarySearch;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public int indexOf(Resume resume) {
        return binarySearch(storage, 0, size(), resume);
    }

    @Override
    protected void saveResume(Resume resume, int index) {
        int insertPoint = -index - 1;
        System.arraycopy(storage, insertPoint, storage, insertPoint + 1,size() - insertPoint);
        storage[insertPoint] = resume;
    }

    @Override
    protected void deleteResume(Resume resume, int index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
    }

}

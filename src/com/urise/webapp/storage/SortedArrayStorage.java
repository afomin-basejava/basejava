package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import static java.util.Arrays.binarySearch;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return binarySearch(storage, 0, size(), searchKey);
    }

    @Override
    protected void addResumeToStorage(Resume resume, int index) { // insert resume
        int insertPoint = -index - 1;
        System.arraycopy(storage, insertPoint, storage, insertPoint + 1,size() - insertPoint);
        storage[insertPoint] = resume;
    }

    @Override
    protected void deleteResumeFromStorage(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
    }

}

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
    protected void saveResume(Resume resume, Object index) {
        checkStorageOverflow(resume);
        int pos = (int) index;
        int insertPoint = -pos - 1;
        System.arraycopy(storage, insertPoint, storage, insertPoint + 1,size() - insertPoint);
        storeResumeByindex(resume,insertPoint);
    }

    @Override
    protected void deleteResume(Object index) {
//        if (size - 1 - index >= 0) {
        int pos = (int) index;
        System.arraycopy(storage, pos + 1, storage, pos, size - 1 - pos);
        resize();
//        }
    }

}

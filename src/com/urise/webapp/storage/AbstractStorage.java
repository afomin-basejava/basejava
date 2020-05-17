package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

// if resume doesn't exist in storage ************************* // throw ExistStorageException if resume is exist
    @Override
    public void save(Resume resume) {
        Object searchKey = checkExistStorageException(resume.getUuid());
        saveResume(resume, searchKey);
    }

// if resume exist in storage ********************************* // throw NotExistStorageException if resume is absent
    @Override
    public Resume get(String uuid) {
        Object index = checkNotExistStorageException(uuid);
        return getResume(index);
    }

    @Override
    public void delete(String uuid) {
        Object index = checkNotExistStorageException(uuid);
        deleteResume(index);
    }

    @Override
    public void update(Resume resume) {
        Object index = checkNotExistStorageException(resume.getUuid());
        updateResume(resume, index);
    }

//**********************************************g*************************

    protected abstract Object getSearchKey(String searchKey);

    protected abstract void saveResume(Resume resume, Object searchKey);

    protected abstract void deleteResume(Object searchKey);

    protected abstract Resume getResume(Object searchKey);

    protected abstract void updateResume(Resume resume, Object searchKey);

//***********************************************************************

    private Object checkExistStorageException(String uuid) { // save not exist resume
        Object index = getSearchKey(uuid);
        if (isExistResume(index)) {    // already exist
            throw new ExistStorageException(uuid);
        }
        return index;
    }

    protected boolean isExistResume(Object key) {
        return key != null;
    }

    private Object checkNotExistStorageException(String uuid) { // get delete update exist resume
        Object index = getSearchKey(uuid);
//        if (index == null) {
        if (!isExistResume(index)) {    // doesn't exist
            throw new NotExistStorageException(uuid);
        }
        return index;
    }
}

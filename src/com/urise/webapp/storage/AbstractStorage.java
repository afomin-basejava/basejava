package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

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
        Object searchKey = checkNotExistStorageException(uuid);
        return getResume(searchKey);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = checkNotExistStorageException(uuid);
        deleteResume(searchKey);
    }

    @Override
    public void update(Resume resume) {
        Object searchKey = checkNotExistStorageException(resume.getUuid());
        updateResume(resume, searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        Comparator<Resume> resumeComparator = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);
        List<Resume> list = getAllUnsorted();
        list.sort(resumeComparator);
//        Collections.sort(list, resumeComparator);
        return list;
    }


//**********************************************g*************************

    protected abstract Object getSearchKey(String searchKey);

    protected abstract void saveResume(Resume resume, Object searchKey);

    protected abstract void deleteResume(Object searchKey);

    protected abstract Resume getResume(Object searchKey);

    protected abstract void updateResume(Resume resume, Object searchKey);

    protected abstract List<Resume> getAllUnsorted();   // unsorted List<Resume>

//***********************************************************************

    private Object checkExistStorageException(String uuid) { // save not exist resume
        Object searchKey = getSearchKey(uuid);
        if (isExistResume(searchKey)) {    // already exist
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected boolean isExistResume(Object searchKey) {
        return searchKey != null;
    }

    private Object checkNotExistStorageException(String uuid) { // get delete update exist resume
        Object searchKey = getSearchKey(uuid);
//        if (index == null) {
        if (!isExistResume(searchKey)) {    // doesn't exist
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
}

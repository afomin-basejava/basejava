package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public abstract void clear();

    @Override
    public void save(Resume resume) {
        int index = checkExistStorageException(resume.getUuid());
        saveResume(resume, index);
    }

    @Override
    public Resume get(String uuid) {
        int index = checkNotExistStorageException(uuid);
        return getResume(uuid, index);
    }

    @Override
    public void delete(String uuid) {
        int index = checkNotExistStorageException(uuid);
        deleteResume(uuid, index);
    }

    @Override
    public void update(Resume resume) {
        int index = checkNotExistStorageException(resume.getUuid());
        updateResume(resume, index);
    }

    @Override
    public abstract Resume[] getAll();

    @Override
    public abstract int size();

//***********************************************************************

    protected abstract int getIndex(String uuid);

    protected abstract void saveResume(Resume resume, int index);

    protected abstract void deleteResume(String uuid, int index);

    protected abstract Resume getResume(String uuid, int index);

    protected abstract void updateResume(Resume resume, int index);

//***********************************************************************

    private int checkExistStorageException(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }
    private int checkNotExistStorageException(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }
}

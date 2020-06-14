package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

//    protected final Logger logger = Logger.getLogger(getClass().getName());
    private final static Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

// if resume doesn't exist in storage ************************* // throw ExistStorageException if resume is exist
    @Override
    public void save(Resume resume) {
        LOG.info("save: " + resume);
        SK searchKey = checkExistStorageException(resume.getUuid());
        saveResume(resume, searchKey);
    }

// if resume exist in storage ********************************* // throw NotExistStorageException if resume is absent
    @Override
    public Resume get(String uuid) {
        LOG.info("get: " + uuid);
        SK searchKey = checkNotExistStorageException(uuid);
        return getResume(searchKey);
    }

    @Override
    public void delete(String uuid) {
        LOG.info("delete: " + uuid);
        SK searchKey = checkNotExistStorageException(uuid);
        deleteResume(searchKey);
    }

    @Override
    public void update(Resume resume) {
        LOG.info("delete: " + resume);
        SK searchKey = checkNotExistStorageException(resume.getUuid());
        updateResume(resume, searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted: " );
        Comparator<Resume> resumeComparator = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);
        List<Resume> list = getAll();
        list.sort(resumeComparator);
//        Collections.sort(list, resumeComparator);
        return list;
    }


//**********************************************g*************************

    protected abstract SK getSearchKey(String searchKey);

    protected abstract void saveResume(Resume resume, SK searchKey);

    protected abstract void deleteResume(SK searchKey);

    protected abstract Resume getResume(SK searchKey);

    protected abstract void updateResume(Resume resume, SK searchKey);

    protected abstract List<Resume> getAll();   // unsorted List<Resume>

    protected boolean isExistResume(SK searchKey) {
        return searchKey != null;
    }

//***********************************************************************

    private SK checkExistStorageException(String uuid) { // save not exist resume
        SK searchKey = getSearchKey(uuid);
        if (isExistResume(searchKey)) {    // already exist
            LOG.warning("checkExistStorageException: " + "Resume already exists!" + uuid);
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK checkNotExistStorageException(String uuid) { // get delete update exist resume
        SK searchKey = getSearchKey(uuid);
//        if (index == null) {
        if (!isExistResume(searchKey)) {    // doesn't exist
            LOG.warning("checkExistStorageException: " + "Resume doesn't exist!" + uuid);
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
}

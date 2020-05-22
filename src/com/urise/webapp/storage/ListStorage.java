package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> listStorage = new ArrayList<>();

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    public int size() {
        return listStorage.size();
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        int index = -1;
        for (Resume resume : listStorage) {
            index++;
            if (resume.getUuid().equals(uuid)) {
                return index;
            }
        }
        return null;
    }

    @Override
    protected void saveResume(Resume resume, Object index) {
        listStorage.add(resume);
    }

    @Override
    protected void deleteResume(Object index) {
        listStorage.remove((int) index);
    }

    @Override
    protected Resume getResume(Object index) {
        return listStorage.get((int) index);
    }

    @Override
    protected void updateResume(Resume resume, Object index) {
        listStorage.set((int) index,resume);
    }

    @Override
    protected List<Resume> getAllUnsorted() {
        return new ArrayList<>(listStorage);
    }

}

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
    public Resume[] getAll() {
        return listStorage.toArray(new Resume[size()]);
    }

    @Override
    public int size() {
        return listStorage.size();
    }

    @Override
    protected Integer getIndex(String uuid) {
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
//        int index = (int) indeX;
        listStorage.remove((int) index);
    }

    @Override
    protected Resume getResume(Object index) {
//        int index = (Integer) indeX;
        return listStorage.get((int) index);
    }

    @Override
    protected void updateResume(Resume resume, Object index) {
//        int index = (Integer) indeX;
        listStorage.set((int) index,resume);
    }

}

package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    List<Resume> listStorage = new ArrayList<>();

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
    protected void deleteResume(Object indeX) {
        int index = (Integer) indeX;
        listStorage.remove(index);
    }

    @Override
    protected Resume getResume(Object indeX) {
        int index = (Integer) indeX;
        return listStorage.get(index);
    }

    @Override
    protected void updateResume(Resume resume, Object indeX) {
        int index = (Integer) indeX;
        listStorage.set(index,resume);
    }

}

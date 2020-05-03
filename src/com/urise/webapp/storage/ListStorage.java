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
    protected int getIndex(String uuid) {
        int index = -1;
        for (Resume resume : listStorage) {
            index++;
            if (resume.getUuid().equals(uuid)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    protected void saveResume(Resume resume, int index) {
        listStorage.add(resume);
    }

    @Override
    protected void deleteResume(String uuid, int index) {
        listStorage.remove(index);
    }

    @Override
    protected Resume getResume(String uuid, int index) {
        return listStorage.get(index);
    }

    @Override
    protected void updateResume(Resume resume, int index) {
        listStorage.set(index,resume);
    }

}

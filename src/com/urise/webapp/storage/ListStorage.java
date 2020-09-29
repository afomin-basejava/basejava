package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private final List<Resume> listStorage = new ArrayList<>();

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
    protected void doSave(Resume resume, Integer index) {
        listStorage.add(resume);
    }

    @Override
    protected void doDelete(Integer index) {
        listStorage.remove(index.intValue());
    }

    @Override
    protected Resume doGet(Integer index) {
        return listStorage.get(index);
    }

    @Override
    protected void doUpdate(Resume resume, Integer index) {
        listStorage.set(index,resume);
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(listStorage);
    }

}

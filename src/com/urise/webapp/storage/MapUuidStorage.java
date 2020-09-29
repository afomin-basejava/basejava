package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {

    private final Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    protected String getSearchKey(String uuid) {
        if (map.get(uuid) != null)
            return uuid;
        return null;
    }

    @Override
    protected void doSave(Resume resume, String key) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(String key) {
        map.remove(key);
    }

    @Override
    protected Resume doGet(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void doUpdate(Resume resume, String key) {
        map.replace(resume.getUuid(), resume);
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(map.values());
    }

}

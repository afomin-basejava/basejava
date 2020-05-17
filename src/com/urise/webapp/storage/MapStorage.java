package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[map.size()]);
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
    protected void saveResume(Resume resume, Object key) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(Object key) {
        map.remove(key);
    }

    @Override
    protected Resume getResume(Object uuid) {
        return map.get(uuid);
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        map.replace(resume.getUuid(), resume);
    }

}

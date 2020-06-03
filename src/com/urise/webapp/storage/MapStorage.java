package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage<String> {

    private Map<String, Resume> map = new HashMap<>();

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
    protected void saveResume(Resume resume, String key) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(String key) {
        map.remove(key);
    }

    @Override
    protected Resume getResume(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void updateResume(Resume resume, String key) {
        map.replace(resume.getUuid(), resume);
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(map.values());
    }

}

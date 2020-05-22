package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorageResume extends AbstractStorage {

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
    protected Resume getSearchKey(String uuid) {
        if (map.get(uuid) != null)
            return map.get(uuid);
        return null;
    }

    @Override
    protected void saveResume(Resume resume, Object key) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(Object resume) {
        String key = ((Resume) resume).getUuid();
        map.remove(key);
    }

    @Override
    protected Resume getResume(Object resume) {
        String key = ((Resume) resume).getUuid();
        return map.get(key);
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        map.replace(resume.getUuid(), resume);
    }

    @Override
    protected List<Resume> getAllUnsorted() {
        return new ArrayList<>(map.values());
    }

}

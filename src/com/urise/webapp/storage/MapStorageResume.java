package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorageResume extends AbstractStorage<Resume> {

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
    protected void doSave(Resume resume, Resume key) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Resume resume) {
        String key = (resume).getUuid();
        map.remove(key);
    }

    @Override
    protected Resume doGet(Resume resume) {
        String key = (resume).getUuid();
        return map.get(key);
    }

    @Override
    protected void doUpdate(Resume resume, Resume key) {
        map.replace(resume.getUuid(), resume);
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(map.values());
    }

}

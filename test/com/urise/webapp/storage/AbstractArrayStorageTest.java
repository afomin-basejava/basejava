package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    static final int CAPACITY = 4;
    protected final Storage storage;
    protected  Resume resume = new Resume("uuid1");

    protected AbstractArrayStorageTest(Class storageType) throws IllegalAccessException, InstantiationException {
       storage = (Storage) storageType.newInstance();
    }

    @Before
    public void fillStorage() {
        storage.save(new Resume(UUID.randomUUID().toString()));
        storage.save(new Resume(UUID.randomUUID().toString()));
        storage.save(resume);
    }
    @After
    public void clearStorage() {
        storage.clear();
    }

    @Test
    public void testClear() {
        storage.clear();
        assertThat(storage.size(), is(0));
    }

    @Test
    public void testSave() {
        int oldSize = storage.size();
        storage.save(new Resume());
        assertEquals(oldSize + 1, storage.size());
    }

    @Test(expected = StorageException.class)
    public void testSaveStorageException() {
        while (storage.size() < CAPACITY) {
            storage.save(new Resume());
        }
        storage.save(new Resume());
    }

    @Test
    public void testGet() {
        Resume testResume = storage.get(resume);
        assertEquals(resume, testResume);
    }
    @Test(expected = NotExistStorageException.class)
    public void testGetWithException() {
        storage.get(new Resume("dummy"));
    }

    @Test
    public void testDelete() {
        int oldSize = storage.size();
        storage.delete(resume);
        assertTrue(storage.size() == oldSize - 1);
    }

    @Test(expected = StorageException.class)
    public void testDeleteException() throws StorageException{
        int oldSize = storage.size();
        storage.get(new Resume("dummy"));
        assertTrue(storage.size() == oldSize);
    }

    @Test
    public void testUpdate() {
        String uuid = resume.getUuid();
        storage.update(resume);
        String uuidafterupdate = storage.get(resume).getUuid();
        assertSame(uuidafterupdate, uuid);
    }

    @Test(expected = RuntimeException.class)
    public void testUpdateException() throws RuntimeException {
        storage.update(new Resume());
    }

    @Test
    public void testGetAll() {
        Resume[] resumes = storage.getAll();
        assertArrayEquals(resumes, storage.getAll());
        assertEquals(resumes.length, storage.size());
    }

    @Test
    public void testSize() {
        int size = storage.size();
    }


}
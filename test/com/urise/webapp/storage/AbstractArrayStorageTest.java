package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import static com.urise.webapp.storage.AbstractArrayStorage.CAPACITY;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private static final int ARRAY_CAPACITY = CAPACITY;
    private final Storage storage;
    private  Resume resume = new Resume("uuid1");

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
        while (storage.size() < ARRAY_CAPACITY) {
            storage.save(new Resume());
        }
        storage.save(new Resume());
    }

    @Test
    public void testGet() {
        Resume resumeForTest = storage.get(resume);
        assertEquals(resume, resumeForTest);
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
        Resume resumeForUpdate = resume;
        storage.update(resumeForUpdate);
        resume = storage.get(resumeForUpdate);
        assertEquals(resumeForUpdate, resume);
    }

    @Test(expected = RuntimeException.class)
    public void testUpdateException() throws RuntimeException {
        storage.update(new Resume());
    }

    @Test
    public void testGetAll() {
        storage.clear();
        int limit = new Random().nextInt(ARRAY_CAPACITY);
        Resume[] initial = new Resume[limit];
        for (int i = 0; i < limit; i++) {
            initial[i] = new Resume("uuid_" + i);
            storage.save(initial[i]);
        }
        Resume[] resumesFromStorage = storage.getAll();
        assertTrue(Arrays.asList(initial).containsAll(Arrays.asList(resumesFromStorage)));
        assertEquals(resumesFromStorage.length, storage.size());
    }

    @Test
    public void testSize() {
        assertEquals(storage.size(), 3);
    }


}
package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static com.urise.webapp.storage.AbstractArrayStorage.CAPACITY;
import static java.util.Arrays.sort;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private String uuid1 = "uuid1";
    private Resume resume = new Resume(uuid1);

    private static int sizeOfStorage = 0;  // for storage size() testing

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void fillStorage() {
        storage.save(resume);
        sizeOfStorage++;
        for (int i = 0; i < new Random().nextInt(CAPACITY - 1); i++) {
            storage.save(new Resume());
            sizeOfStorage++;
        }
    }
    @After
    public void clearStorage() {
        storage.clear();
        sizeOfStorage = 0;
    }

    @Test
    public void clearTest() {
        storage.clear();
        assertThat(storage.size(), is(0));
    }

    @Test
    public void saveTest() {
        int oldSize = storage.size();
        resume = new Resume("uuid2");
        storage.save(resume);
        assertEquals(oldSize + 1, storage.size());
        assertTrue(checkExistResume(resume));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistingResumeTest() {
        storage.save(resume);
    }

    @Test(expected = StorageException.class)
    public void saveWithStorageExceptionTest() {
        while (storage.size() < CAPACITY) {
            storage.save(new Resume());
        }
        storage.save(new Resume());
    }

    @Test
    public void getTest() {
        Resume resumeForTest = storage.get(uuid1);
        assertEquals(resume, resumeForTest);
    }
    @Test(expected = NotExistStorageException.class)
    public void getWithExceptionTest() {
        storage.get("dummy");
    }

    @Test
    public void deleteTest() {
        int oldSize = storage.size();
        storage.delete(resume.getUuid());
        assertEquals(oldSize - 1, storage.size());
        assertFalse(checkExistResume(resume));
    }

    @Test(expected = StorageException.class)
    public void deleteWithExceptionTest() throws StorageException{
        int oldSize = storage.size();
        storage.delete("dummy");
        assertEquals(storage.size(), oldSize);
    }

    @Test
    public void updateTest() {
        Resume resumeForUpdate = resume;
        storage.update(resumeForUpdate);
        resume = storage.get(resumeForUpdate.getUuid());
        assertEquals(resumeForUpdate, resume);
    }

    @Test(expected = RuntimeException.class)
    public void updateWithExceptionTest() throws RuntimeException {
        storage.update(new Resume());
    }

    @Test
    public void getAllTest() {
        storage.clear();
        int limit = new Random().nextInt(CAPACITY);
        Resume[] initial = new Resume[limit];
        for (int i = 0; i < limit; i++) {
            initial[i] = new Resume("uuid_" + i);
            storage.save(initial[i]);
        }
        Resume[] resumesFromStorage = storage.getAll();
        sort(resumesFromStorage);
        sort(initial);
        assertArrayEquals(resumesFromStorage, initial);
        assertEquals(resumesFromStorage.length, storage.size());
    }

    @Test
    public void sizeTest() {
        assertEquals(storage.size(), sizeOfStorage);
    }

    private boolean checkExistResume(Resume resume) {
        boolean check = true;
        try {
            storage.get(resume.getUuid());
        } catch (NotExistStorageException se) {
            check = false;
        }
        return check;
    }
}
package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.urise.webapp.storage.ResumeTestData.*;
import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected static final String RESUME_STORAGE_DIRECTORY = "D:\\basejava\\storage";

    private final Storage storage;

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void fillStorage() {
        storage.clear();
        storage.save(RESUME_GRIGORY_KISLIN);
        storage.save(RESUME_2);
    }

    @After
    public void clearStorage() {
//        storage.clear();
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void save() {
        storage.save(RESUME_3);
        assertSize(3);
        assertGet(RESUME_3);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistingResume() {
        storage.save(RESUME_GRIGORY_KISLIN);
    }

    @Test
    public void get() {
        assertGet(RESUME_GRIGORY_KISLIN);
        assertGet(RESUME_2);
        storage.save(RESUME_3);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistResume() {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(RESUME_GRIGORY_KISLIN.getUuid());
        assertSize(1);
        storage.delete(RESUME_2.getUuid());
        assertSize(0);
        storage.get(RESUME_2.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistResume() {
        storage.delete("dummy");
        assertSize(2);
    }

    @Test
    public void update() {
        Resume resume = RESUME_2;
        RESUME_2.setContact(ContactType.LINKEDIN, "LINKEDIN");
        storage.update(resume);
        assertGet(resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistResume() {
        storage.update(ResumeTestData.createResume("DUMMY", "FULL_NAME_DUMMY"));
    }

    @Test
    public void getAllSorted() {
        assertEquals(storage.getAllSorted(), Arrays.asList(RESUME_2, RESUME_GRIGORY_KISLIN));
    }

    @Test
    public void size() {
        assertSize(2);
    }

    private void assertSize(int expectedSize) {
        assertEquals(expectedSize, storage.size());
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

}
package com.urise.webapp.storage;

import java.nio.file.Paths;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {
    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage(Paths.get(RESUME_STORAGE_DIRECTORY)));
    }
}
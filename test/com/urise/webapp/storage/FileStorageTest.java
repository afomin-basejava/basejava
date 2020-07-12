package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.ObjectStreamSerializer;

import java.io.File;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(new File(RESUME_STORAGE_DIRECTORY), new ObjectStreamSerializer()));
    }
}
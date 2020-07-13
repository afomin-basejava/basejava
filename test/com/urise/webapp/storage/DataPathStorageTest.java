package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.DataStreamSerializer;

import java.nio.file.Paths;

public class DataPathStorageTest extends AbstractStorageTest {
    public DataPathStorageTest() {
        super(new PathStorage(Paths.get(RESUME_STORAGE_DIRECTORY), new DataStreamSerializer()));
    }
}
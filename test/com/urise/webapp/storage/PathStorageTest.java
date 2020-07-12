package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.ObjectStreamSerializer;

import java.nio.file.Paths;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage(Paths.get(RESUME_STORAGE_DIRECTORY), new ObjectStreamSerializer()));
    }
}
package com.urise.webapp.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {
    private static final Path RESUME_DIRECTORY= Paths.get("D:\\basejava\\storage");

    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage(RESUME_DIRECTORY));
    }
}
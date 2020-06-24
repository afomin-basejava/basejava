package com.urise.webapp.storage;

import java.io.File;

public class ObjectStreamStorageTest extends AbstractStorageTest {
    private static final File RESUME_DIRECTORY= new File("D:\\basejava\\storage");

    public ObjectStreamStorageTest() {
        super(new ObjectStreamStorage(RESUME_DIRECTORY));
    }
}
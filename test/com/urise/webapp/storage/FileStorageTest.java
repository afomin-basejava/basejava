package com.urise.webapp.storage;

import java.io.File;

public class FileStorageTest extends AbstractStorageTest {
    private static final File RESUME_DIRECTORY= new File("D:\\basejava\\storage");
    private static final SerializerStrategy SERIALIZE_STRATEGY = new ObjectStreamSerializer();
    public FileStorageTest() {
        super(new FileStorage(RESUME_DIRECTORY, SERIALIZE_STRATEGY));
    }
}
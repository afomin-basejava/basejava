package com.urise.webapp.storage;

import com.urise.webapp.storage.serializerstrategy.ObjectStreamSerializer;
import com.urise.webapp.storage.serializerstrategy.SerializerStrategy;

import java.io.File;

public class FileStorageTest extends AbstractStorageTest {
    private static final SerializerStrategy SERIALIZE_STRATEGY = new ObjectStreamSerializer();
    public FileStorageTest() {
        super(new FileStorage(new File(RESUME_STORAGE_DIRECTORY), SERIALIZE_STRATEGY));
    }
}
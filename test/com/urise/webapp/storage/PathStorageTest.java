package com.urise.webapp.storage;

import com.urise.webapp.storage.serializerstrategy.ObjectStreamSerializer;
import com.urise.webapp.storage.serializerstrategy.SerializerStrategy;

import java.nio.file.Paths;

public class PathStorageTest extends AbstractStorageTest {
    private static final SerializerStrategy SERIALIZE_STRATEGY = new ObjectStreamSerializer();
    public PathStorageTest() {
        super(new PathStorage(Paths.get(RESUME_STORAGE_DIRECTORY), SERIALIZE_STRATEGY));
    }
}
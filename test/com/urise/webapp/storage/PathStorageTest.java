package com.urise.webapp.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathStorageTest extends AbstractStorageTest {
    private static final Path RESUME_DIRECTORY = Paths.get("D:\\basejava\\storage");
    private static final SerializerStrategy SERIALIZE_STRATEGY = new ObjectStreamSerializer();
    public PathStorageTest() {
        super(new PathStorage(RESUME_DIRECTORY, SERIALIZE_STRATEGY));
    }
}
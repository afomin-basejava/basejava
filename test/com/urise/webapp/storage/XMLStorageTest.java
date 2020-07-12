package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.XMLStreamSerializer;

import java.nio.file.Paths;

public class XMLStorageTest extends AbstractStorageTest {
    public XMLStorageTest() {
        super(new PathStorage(Paths.get(RESUME_STORAGE_DIRECTORY), new XMLStreamSerializer()));
    }
}
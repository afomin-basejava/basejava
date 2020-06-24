package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Path;

public class ObjectStreamPathStorage extends AbstractPathStorage {

    public ObjectStreamPathStorage(Path directory) {
        super(directory);
    }

    @Override
    protected void doWrite(Resume resume, OutputStream file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(file)) {
            oos.writeObject(resume);
        }
    }

    @Override
    protected Resume doRead(InputStream file) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(file)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}

package com.urise.webapp.storage.serializerstrategy;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;

public class ObjectStreamSerializer implements SerializerStrategy {
// File Path serializer
    @Override
    public void doWrite(Resume resume, OutputStream file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(file)) {
            oos.writeObject(resume);
        }
    }

    @Override
    public Resume doRead(InputStream file) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(file)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", file.toString(), e);
        }
    }
}

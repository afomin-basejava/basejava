package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serializerstrategy.SerializerStrategy;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileStorage extends AbstractFileStorage {
    private SerializerStrategy serializerStrategy;

    public FileStorage(File directory, SerializerStrategy serializerStrategy) {
        super(directory);
        this.serializerStrategy = serializerStrategy;
    }

    @Override
    protected void doWrite(Resume resume, OutputStream file) throws IOException {
        serializerStrategy.doWrite(resume, file);
    }

    @Override
    protected Resume doRead(InputStream file) throws IOException, ClassNotFoundException {
        return serializerStrategy.doRead(file);
    }
}
package com.urise.webapp.storage.serializerstrategy;

import com.urise.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class XMLSerializer implements SerializerStrategy {
    @Override
    public void doWrite(Resume resume, OutputStream file) throws IOException {

    }

    @Override
    public Resume doRead(InputStream file) throws IOException, ClassNotFoundException {
        return null;
    }
}

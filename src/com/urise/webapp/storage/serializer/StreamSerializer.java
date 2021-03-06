package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamSerializer {
    // write resume into file in appropriate format
    void doWrite(Resume resume, OutputStream file) throws IOException;
    // read resume from file in appropriate format
    Resume doRead(InputStream file) throws IOException, ClassNotFoundException;
}

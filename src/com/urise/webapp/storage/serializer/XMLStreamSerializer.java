package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;
import com.urise.webapp.util.XmlParser;

import java.io.*;

public class XMLStreamSerializer implements StreamSerializer {
    private final XmlParser xmlParser;
    public XMLStreamSerializer() {
        xmlParser = new XmlParser(Resume.class, Organization.class, Organization.Job.class,
                OrganizationSection.class, ListSection.class, TextSection.class);
    }

    @Override
    public void doWrite(Resume resume, OutputStream file) throws IOException {
        try (Writer writer = new OutputStreamWriter(file)) {
            xmlParser.marshal(resume, file);
        }
    }

    @Override
    public Resume doRead(InputStream file) throws IOException {
        try (Reader reader = new InputStreamReader(file)){
            return xmlParser.unmarshal(file);
        }
    }
}

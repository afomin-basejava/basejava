package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.AbstractSection;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;

import java.io.*;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {
// File Path serializer
    @Override
    public void doWrite(Resume resume, OutputStream file) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(file)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, AbstractSection> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                SectionType sectionType = entry.getKey();
                dos.writeUTF(sectionType.name());
                dos.writeUTF(entry.getValue().toString());
            }
        }
    }

    @Override
    public Resume doRead(InputStream file) throws IOException {
        try (DataInputStream dis = new DataInputStream(file)) {
            return null;
        }
    }
}

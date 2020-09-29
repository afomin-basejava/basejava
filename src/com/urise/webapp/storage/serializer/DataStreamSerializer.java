package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataStreamSerializer implements StreamSerializer {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void doWrite(Resume resume, OutputStream file) throws IOException {
        try (final DataOutputStream dos = new DataOutputStream(file)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            writeCollection(resume.getContacts().entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(String.valueOf(entry.getValue()));
            });
            writeCollection(resume.getSections().entrySet(), dos, entry -> {
                SectionType sectionType = entry.getKey();
                dos.writeUTF(entry.getKey().name());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(String.valueOf(entry.getValue()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeCollection(((ListSection) entry.getValue()).getListSection(), dos, dos::writeUTF);
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        writeCollection(((OrganizationSection) entry.getValue()).getOrganizations(), dos, organization -> {
                            dos.writeUTF(organization.getName());
                            dos.writeUTF(organization.getUrl() == null ? "empty" : organization.getUrl());
                            writeCollection(organization.getJobs(), dos, job -> {
                                dos.writeUTF(job.getName());
                                dos.writeUTF(job.getStartDate().format(dtf));
                                dos.writeUTF(job.getFinishDate().format(dtf));
                                dos.writeUTF(job.getDescription() == null ? "empty" : job.getDescription());
                            });
                        });
                         break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + sectionType);
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream file) throws IOException {
        try (DataInputStream dis = new DataInputStream(file)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readResumePartitions(dis, () -> {
                ContactType contactType = ContactType.valueOf(dis.readUTF());
                resume.setContact(contactType, dis.readUTF());
            });
            readResumePartitions(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                AbstractSection result;
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        result = new TextSection(dis.readUTF());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        result = new ListSection(readList(dis, dis::readUTF));
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        result = new OrganizationSection(
                                readList(dis,
                                        () -> new Organization(dis.readUTF(), readStringUTF(dis),
                                                readList(dis,
                                                        () -> new Organization.Job(dis.readUTF(), readLocalDate(dis), readLocalDate(dis), readStringUTF(dis))
                                                )
                                        )
                                )
                        );
                        break;
                    default:
                            throw new IllegalStateException("Unexpected value: " + sectionType);
                }
                resume.setSection(sectionType, result);
            });
            return resume;
        }
    }

    private <T> void writeCollection(Collection<T> collection, DataOutputStream dos, WriteCollectionElement<T> wce) throws IOException {
        dos.writeInt(collection.size());
        for (T element : collection) {
            wce.writeCollectionElement(element);
        }
    }

    private interface WriteCollectionElement<T> {
        void writeCollectionElement(T element) throws IOException;
    }


    private void readResumePartitions(DataInputStream dis, ReadResumePartition re) throws IOException {
        int size = dis.readInt();           // EnumMaps size
        for (int i = 0; i < size; i++) {
            re.readPartition();
        }
    }

    private interface ReadResumePartition {
        void readPartition() throws IOException;
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.parse(dis.readUTF(), dtf);
    }

    private String readStringUTF(DataInputStream dis) throws IOException {
        String url = dis.readUTF();
        if (url.equals("empty")) url = null;
        return url;
    }

    private <T> List<T> readList(DataInputStream dis, ReadListElement<T> rle) throws IOException {
        List<T> list = new ArrayList<>();
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            list.add(rle.readListElement());
        }
        return list;
    }

    interface ReadListElement<T> {
        T readListElement() throws IOException;
    }
}

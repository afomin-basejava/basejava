package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataStreamSerializer implements StreamSerializer {
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
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(String.valueOf(entry.getValue()));
            });
        }
    }

    @Override
    public Resume doRead(InputStream file) throws IOException {
        try (DataInputStream dis = new DataInputStream(file)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readElements(dis, () -> resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readElements(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.setSection(sectionType, readSection(dis, sectionType));
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

    private void readElements(DataInputStream dis, ReadElement re) throws IOException {
        int size = dis.readInt();           // EnumMaps size
        for (int i = 0; i < size; i++) {
            re.readElement();
        }
    }

    private interface ReadElement {
        void readElement() throws IOException;
    }

    private AbstractSection readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] sectionAsStringArray = dis.readUTF().split("\n");
        switch (sectionType) {
            case OBJECTIVE:
            case PERSONAL:
                return new TextSection(sectionAsStringArray[0]);
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                return new ListSection(new ArrayList<>(Arrays.asList(sectionAsStringArray)));
            case EDUCATION:
            case EXPIRIENCE:
                List<Organization> organizations = new ArrayList<>();
                int index = 0;
                int numberOfOrganization = Integer.parseInt(sectionAsStringArray[index++]);
                for (int organizationNumber = 0; organizationNumber < numberOfOrganization; organizationNumber++) {
                    List<Organization.Job> jobs = new ArrayList<>();
                    String organ = sectionAsStringArray[index++];
                    String url = sectionAsStringArray[index++];
                    int numberOfJobs = Integer.parseInt(sectionAsStringArray[index++]);
                    for (int jobNumber = 0; jobNumber < numberOfJobs; jobNumber++) {
                        LocalDate startDate = LocalDate.parse(sectionAsStringArray[index++], dtf);
                        LocalDate finishDate = LocalDate.parse(sectionAsStringArray[index++], dtf);
                        String name = sectionAsStringArray[index++];
                        String description = sectionAsStringArray[index++];
                        if (description.equals("empty")) {
                            description = "";
                        }
                        jobs.add(new Organization.Job(name, startDate, finishDate, description));
                    }
                    organizations.add(new Organization(organ, url, jobs));
                }
                return new OrganizationSection(organizations);
            default:
                throw new IllegalStateException("Unexpected value: " + sectionType);
        }
    }

}

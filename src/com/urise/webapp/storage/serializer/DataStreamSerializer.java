package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataStreamSerializer implements StreamSerializer {
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
                dos.writeUTF(String.valueOf(entry.getValue()));
            }
        }
    }

    @Override
    public Resume doRead(InputStream file) throws IOException {
        Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
        Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try (DataInputStream dis = new DataInputStream(file)) {
            //---------------------------------------------------------------------------------
            String uuid = dis.readUTF();
            String fullname = dis.readUTF();
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                ContactType contactType = ContactType.valueOf(dis.readUTF());
                String contact = dis.readUTF();
                contacts.put(contactType, contact);
            }
            //---------------------------------------------------------------------------------
            int numberOfSections = dis.readInt();
            for (int i = 0; i < numberOfSections; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        sections.put(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        sections.put(sectionType, new ListSection(new ArrayList<>(Arrays.asList(dis.readUTF().split("\n")))));
                        break;
                    case EDUCATION:
                    case EXPIRIENCE:
                        List<Organization> organizations = new ArrayList<>();
                        String[] sectionAsString = dis.readUTF().split("\n");
                        int index = 0;
                        int numberOfOrganization = Integer.parseInt(sectionAsString[index++]);
                        for (int organizationNumber = 0; organizationNumber < numberOfOrganization; organizationNumber++) {
                            List<Organization.Job> jobs = new ArrayList<>();
                            String organ = sectionAsString[index++];
                            String url = sectionAsString[index++];
                            int numberOfJobs = Integer.parseInt(sectionAsString[index++]);
                            for (int jobNumber = 0; jobNumber < numberOfJobs; jobNumber++) {
                                LocalDate startDate = LocalDate.parse(sectionAsString[index++], dtf);
                                LocalDate finishDate = LocalDate.parse(sectionAsString[index++], dtf);
                                String jobName = sectionAsString[index++];
                                String jobDescription = sectionAsString[index++];
                                if (jobDescription.equals("empty")) {
                                    jobDescription = "";
                                }
                                jobs.add(new Organization.Job(jobName, startDate, finishDate, jobDescription));
                            }
                            organizations.add(new Organization(organ, url, jobs));
                        }
                        sections.put(sectionType, new OrganizationSection(organizations));
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + sectionType);
                }
            }
            return new Resume(uuid, fullname, contacts, sections);
        }
    }
}

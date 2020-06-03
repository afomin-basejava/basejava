package com.urise.webapp.model;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume  {
    // Unique identifier
    private final String uuid;
    private final String fullName;
    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public Map<SectionType, Section> getSections() {
        return sections;
    }
    public Section getSection(SectionType type) {
        return sections.get(type);
    }

    public void setSections(SectionType sectionType, Section section) {
        sections.put(sectionType, section);
    }

    public Resume() {
        this(UUID.nameUUIDFromBytes(new byte[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37 , 41}).toString(), "SomeOne");
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String fullName, Map<ContactType, String> contacts) {
        this(UUID.randomUUID().toString(), fullName);
        this.contacts = contacts;
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "Resume constructor: uuid must be non null");
        Objects.requireNonNull(fullName, "Resume constructor: fullName must be non null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }
    public void setContact(ContactType contactType, String text) {
        contacts.put(contactType, text);
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return uuid + " - " + fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return  this.uuid.equals(resume.uuid) &&
                this.fullName.equals(resume.fullName) //&& this.contacts.equals(resume.contacts)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }


}

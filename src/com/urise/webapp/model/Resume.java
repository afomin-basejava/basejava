package com.urise.webapp.model;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * Initial resume class
 */
public class Resume implements Serializable {
    private static final long serialVersionUID = 1L;
    // Unique identifier
    private String uuid;
    private String fullName;
    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);

    public Resume() {
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
    } // addContact

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }
    public AbstractSection getSection(SectionType type) {
        return sections.get(type);
    }
    public void setSection(SectionType sectionType, AbstractSection section) { // addSection
        sections.put(sectionType, section);
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
        final Resume resume = (Resume) o;
        return this.getUuid().equals(resume.getUuid()) &&
            this.getFullName().equals(resume.getFullName())
            &&
            this.getContacts().toString().equals(resume.getContacts().toString())
            &&
            new Predicate<Resume>() {
                @Override
                public boolean test(Resume resume) {
                    if (resume.getSections().size() != (((Resume) o).getSections().size())) {
                        return false;
                    }
                    for (SectionType type : SectionType.values()) {
                        if (resume.getSection(type) != null && ((Resume) o).getSection(type) != null) {
                            if (!resume.getSection(type).toString().equals(((Resume) o).getSection(type).toString())) {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                    return true;
                }
            }.test(this)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getFullName(), getContacts(), getSections());
    }

}

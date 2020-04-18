package com.urise.webapp.model;

import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private String uuid;

    public Resume() {
        this.uuid = UUID.randomUUID().toString();
    }

    public Resume(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Resume resume = (Resume) obj;
        return
                this.getUuid().equals(resume.getUuid());
    }

    @Override
    public int hashCode() {
        return
                this.getUuid().hashCode();
    }

    @Override
    public int compareTo(Resume o) {
        return this.getUuid().compareTo(o.getUuid());
    }
}

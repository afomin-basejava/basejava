package com.urise.webapp.model;

import com.urise.webapp.exception.StorageException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@XmlAccessorType(XmlAccessType.FIELD)
public class ListSection extends AbstractSection {
    private static final long serialVersionUID = 1L;
    private List<String> listSection = new ArrayList<>();

    public ListSection() {}
    public ListSection(List<String> listSection) {
        this.listSection = listSection;
    }
    public ListSection(String text) {
        listSection.add(text);
    }

    public List<String> getListSection() {
        return listSection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(getListSection(), that.getListSection());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getListSection());
    }

    @Override
    public String toString() {
        return listSection.stream().reduce((s1, s2) -> (s1 + "\n") + s2 ).orElseThrow(() -> new StorageException("Wrong ListSection"));
    }

}

package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private List<String> listSection = new ArrayList<>();

    public ListSection(List<String> listSection) {
        this.listSection = listSection;
    }
    public ListSection(String text) {
        listSection.add(text);
    }

    public List<String> getListSection() {
        return listSection;
    }

//    public void setListSection(List<String> listSection) {
//        this.listSection = listSection;
//    }

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
        return listSection.stream().map(s -> s + "\n").reduce("", (s1, s2) -> s1 + s2 );
    }

}

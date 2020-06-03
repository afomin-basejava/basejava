package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListSection extends Section {
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
        return "ListSection{" +
                "listSection=" + listSection +
                '}';
    }

    @Override
    public void printSection(SectionType sectionType, Section section) {
        System.out.println();
        System.out.println(sectionType.getTitle());
        System.out.println();
        listSection.forEach(s -> System.out.println(s + "\n"));
        printSectionDelimeter();
    }

}

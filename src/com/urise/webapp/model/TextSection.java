package com.urise.webapp.model;

import java.util.Objects;

public class TextSection extends Section {

    private String textSection;

    public TextSection() {
        this.textSection = "textSection";
    }
    public TextSection(String textSection) {
        this.textSection = textSection;
    }

    public String getTextSection() {
        return textSection;
    }

    public void setTextSection(String textSection) {
        this.textSection = textSection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return Objects.equals(getTextSection(), that.getTextSection());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTextSection());
    }

    @Override
    public String toString() {
        return textSection;
    }

    @Override
    public void printSection(SectionType sectionType, Section section) {
        System.out.println(sectionType.getTitle() + '\n');
        System.out.println(getTextSection() + "\n");
        printSectionDelimeter();
    }
}

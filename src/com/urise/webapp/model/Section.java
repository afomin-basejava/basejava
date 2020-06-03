package com.urise.webapp.model;

abstract public class Section {
    abstract public void printSection(SectionType sectionType, Section section);
    public void printSectionDelimeter() {
        System.out.println("-------------------------------------------------------");
    }
}

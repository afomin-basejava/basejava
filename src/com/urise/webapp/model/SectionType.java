package com.urise.webapp.model;

public enum SectionType {
    OBJECTIVE("Позиция"),               //class TextSection { String }
    PERSONAL("Личные качества"),        //class TextSection { String }
    ACHIEVEMENT("Достижения"),          //class ListSection { List<String> }
    QUALIFICATIONS("Квалификация"),     //class ListSection { List<String> }
    EXPIRIENCE("Опыт"),                 //class OrgSection  { List<Position>> ... class Position{} }
    EDUCATION("Образование");           //class OrgSection  { List<Position>> ... class Position{} }

    private String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

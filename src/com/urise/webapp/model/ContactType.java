package com.urise.webapp.model;

public enum ContactType {
    RESIDENCE("Russia"),
    PHONE("Телефон"),
    EMAIL("e-mail"),
    SKYPE("Skype"),
    LINKEDIN("LinkedIn"),
    GITHUB("GitHub"),
    STACKOVERFLOW("StackOverflow"),
    HOMEPAGE("HOMEPAGE");
    String title;
    ContactType(String title) {
        this.title = title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
}

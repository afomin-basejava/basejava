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
/*
Тел.: +7(921) 855-0482<br>
String metod(String ref) { return getTitle() + ":" + ref; }

Skype: <a href="skype:grigory.kislin">grigory.kislin</a><br>

Почта: <a href="mailto:gkislin@yandex.ru">gkislin@yandex.ru</a><br>

<a href="https://www.linkedin.com/in/gkislin">Профиль LinkedIn</a><br>

<a href="https://github.com/gkislin">Профиль GitHub</a><br>

<a href="https://stackoverflow.com/users/548473">Профиль Stackoverflow</a><br>

<a href="http://gkislin.ru/">Домашняя страница</a><br>

*/
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

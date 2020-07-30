package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Arrays;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)

public class TextSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private String textSection;

    public TextSection() {
        this.textSection = "textSection";
    }
    public TextSection(String textSection) {
        this.textSection = textSection;
    }
    public TextSection(String[] text) {
        this.textSection = Arrays.stream(text).reduce((s1, s2) -> new StringBuilder().append(s1).append(' ').append(s2).toString()).get();
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
        return getTextSection().equals(that.getTextSection());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTextSection());
    }

    @Override
    public String toString() {
        return getTextSection();
    }

}

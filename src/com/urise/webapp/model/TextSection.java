package com.urise.webapp.model;

import java.util.Objects;

public class TextSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

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
        return getTextSection().equals(that.getTextSection());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTextSection());
    }

    @Override
    public String toString() {
        return getTextSection() + "\n";
    }

}

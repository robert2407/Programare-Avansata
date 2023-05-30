package org.example;

import java.time.LocalDate;

public class Programmer extends Person {
    String language;

    public Programmer(String name, LocalDate birthDate, String language) {
        super(name, birthDate);
        this.language = language;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

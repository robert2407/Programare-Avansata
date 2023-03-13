package org.example;

import java.time.LocalDate;

public class Designer extends Person {
    String gadget;

    public Designer(String name, LocalDate birthDate, String gadget) {
        super(name, birthDate);
        this.gadget = gadget;
    }

    public String setGadget() {
        return this.gadget;
    }

    public void setGadget(String gadget) {
        this.gadget = gadget;
    }
}

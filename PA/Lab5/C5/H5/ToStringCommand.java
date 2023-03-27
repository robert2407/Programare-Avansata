package org.example;

import java.io.IOException;

public class ToStringCommand implements Command {
    private Catalog catalog;

    public ToStringCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public void execute() throws IOException {
        System.out.println(catalog.toString());
    }
}
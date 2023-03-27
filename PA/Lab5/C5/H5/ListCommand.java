package org.example;

import java.util.List;

public class ListCommand implements Command {
    private Catalog catalog;

    public ListCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public void execute() {
        for (Document document : catalog.getDocs()) {
            System.out.println(document.toString());
        }
    }
}

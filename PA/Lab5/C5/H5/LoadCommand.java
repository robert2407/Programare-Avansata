package org.example;

import java.io.IOException;

public class LoadCommand implements Command {
    private String path;

    public LoadCommand(String path) {
        this.path = path;
    }

    @Override
    public void execute() throws InvalidCatalogException, IOException {
        Catalog catalog = CatalogUtil.load(path);
        System.out.println(catalog.toString());
    }
}

package org.example;

import java.io.IOException;

public class SaveCommand implements Command {
    private Catalog catalog;
    private String path;

    public SaveCommand(Catalog catalog, String path) {
        this.catalog = catalog;
        this.path = path;
    }

    @Override
    public void execute() throws IOException {
        CatalogUtil.save(catalog, path);
    }
}
package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InvalidCatalogException {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();
    }

    private void testCreateSave() throws InvalidCatalogException {
            try {
                Catalog catalog = new Catalog("MyDocuments");
                var book = new Document("book");
                var article = new Document("article");
                catalog.add(book);
                catalog.add(article);
                System.out.println(catalog);

                CatalogUtil.save(catalog, "C:\\Users\\Robert\\Desktop\\Lab5\\C5\\catalog.json");
            } catch (IOException exception) {
                throw new InvalidCatalogException(exception);
            }
        }

    private void testLoadView() throws InvalidCatalogException {
        Catalog catalog = CatalogUtil.load("C:\\Users\\Robert\\Desktop\\Lab5\\C5\\catalog.json");
    }
}
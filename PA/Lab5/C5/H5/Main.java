package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InvalidCatalogException {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();
    }

    private void testCreateSave() throws InvalidCatalogException {
            try {
                Catalog catalog = new Catalog("MyDocuments");
                Map<String, Object> tags = new HashMap<>();
                tags.put("table-row", "<tr>");
                var book = new Document("12","book1","C:\\Users\\Robert\\Desktop\\Lab5",tags);
                var article = new Document("13","article","C:\\Users\\Robert\\Desktop\\Lab5",tags);

//                catalog.add(book);
//                catalog.add(article);
//                System.out.println(catalog);
//                CatalogUtil.save(catalog, "C:\\Users\\Robert\\Desktop\\Lab5\\C5\\catalog.json");

                Command addBook = new AddCommand(catalog, book);
                Command addArticle = new AddCommand(catalog, article);
                Command saveCatalog = new SaveCommand(catalog, "C:\\Users\\Robert\\Desktop\\Lab5\\C5\\catalog.json");
                Command reportCommand = new ReportCommand(catalog, "C:\\Users\\Robert\\Desktop\\Lab5\\C5\\report.html");
                Command listCommand = new ListCommand(catalog);

                //     mvn package          // in consola
                //     java -jar test.jar           // in consola

                //      jar cfm test.jar MANIFEST.MF *.java


                addBook.execute();
                addArticle.execute();
                new ToStringCommand(catalog).execute();
                reportCommand.execute();
                listCommand.execute();

                saveCatalog.execute();
            } catch (IOException exception) {
                throw new InvalidCatalogException(exception);
            }
        }

    private void testLoadView() throws InvalidCatalogException {
        Catalog catalog = CatalogUtil.load("C:\\Users\\Robert\\Desktop\\Lab5\\C5\\catalog.json");
    }
}
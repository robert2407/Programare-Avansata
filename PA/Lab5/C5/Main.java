package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Catalog catalog = new Catalog("MyDocuments");
        Document doc = new Document("Carte");
//        List<Catalog> catalog2 = new ArrayList<>();
        var book = new Document("article1");
        catalog.add(book);
        System.out.println(catalog);
//        Wrapper.save(catalog, "d:/research/catalog.json");
    }
}
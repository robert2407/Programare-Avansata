package org.example;

import javax.print.Doc;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable{
    String nameOfDocs;
    String type;
    List<Document> docs = new ArrayList<>();

    public Catalog() {
    }

    public Catalog(String name){
        this.nameOfDocs = name;
    }

    public Catalog(String name, List<Document> documentList){
        this.nameOfDocs = name;
        this.docs = documentList;
    }

    public List<Document> getDocs() {
        return docs;
    }

    public void add(Document document) {
        if (!docs.contains(document)) {
            this.docs.add(document);
        }
    }

    public Document findById(String id) {
        return docs.stream().filter(document -> document.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "nameOfDocs='" + nameOfDocs + '\'' +
                ", type='" + type + '\'' +
                ", docs=" + docs +
                '}';
    }

    public Object getDocuments() {
        return docs;
    }
}

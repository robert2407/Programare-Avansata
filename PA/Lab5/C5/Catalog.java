package org.example;

import javax.print.Doc;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable{
    String nameOfDocs;
    String type;
    List<Document> docs = new ArrayList<>();



    public Catalog(String name){
        this.nameOfDocs = name;
    }

    public void add(Document document) {
        this.docs.add(document) ;
    }

    public Document findById(String id) {
        for (var doc : docs) {
            if (doc.getId().equals(id)) {
                return doc;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "nameOfDocs='" + nameOfDocs + '\'' +
                ", type='" + type + '\'' +
                ", docs=" + docs +
                '}';
    }


}

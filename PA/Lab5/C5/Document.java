package org.example;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Document implements Serializable {
    private String id;
    private String title;
    private String location;
    private Map<String, Object> tags = new HashMap<>();

    public Document(String title){
        this.title = title;
    }
    public void addTag(String key, Object obj) {
        tags.put(key, obj);
    }

    public Object getId() {
        return this.id;
    }
}

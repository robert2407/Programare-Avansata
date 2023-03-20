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

    public Document() {
    }
    public Document(String id, String title, String location, Map<String, Object> tags) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.tags = tags;
    }

    public Document(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, Object> getTags() {
        return tags;
    }

    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
    }
    public void addTags(String key, Object obj) {
        tags.put(key, obj);
    }
}

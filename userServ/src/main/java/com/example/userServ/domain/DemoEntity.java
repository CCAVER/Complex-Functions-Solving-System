package com.example.userServ.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "demo_collection")  //表名
public class DemoEntity implements Serializable {
    @Id //绑定每个表中的_id字段
    private int id;

    private String title;

    public DemoEntity() {
    }

    public DemoEntity(String title, String description, String by, String url, String corn) {
        this.title = title;
        this.description = description;
        this.by = by;
        this.url = url;
        this.corn = corn;
    }

    private String description;

    private String by;

    public DemoEntity(int id, String title, String description, String by, String url, String corn) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.by = by;
        this.url = url;
        this.corn = corn;
    }

    private String url;

    private String corn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getCorn() {
        return corn;
    }

    public void setCorn(String corn) {
        this.corn = corn;
    }
}

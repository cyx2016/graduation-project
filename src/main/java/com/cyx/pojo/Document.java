package com.cyx.pojo;

import org.springframework.stereotype.Component;

@Component
public class Document {
    private String id;

    private String kind;

    private String kindname;

    private String name;

    private String author;

    private String authorname;

    private String url;

    private String level;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getKindName() {
        return kindname;
    }

    public void setKindName(String kindname) {
        this.kindname = kindname == null ? null : kindname.trim();
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind == null ? null : kind.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getAuthorName() {
        return authorname;
    }

    public void setAuthorName(String authorname) {
        this.authorname = authorname == null ? null : authorname.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }
}
package com.example.jpa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Intro {
    @Column(table = "writer_intro", name = "content_Type")
    private String contentType;

    @Column(table = "writer_intro", name="content")
    private String content;

    public Intro() {
    }

    public Intro(String contentType, String content) {
        this.contentType = contentType;
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public String getContent() {
        return content;
    }
}

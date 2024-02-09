package com.example.jpa.domain;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="doc")
public class Document {
    @Id
    private String id;
    private String title;
    private String content;

    @ElementCollection
    @CollectionTable(
            name="doc_prop",
            joinColumns = @JoinColumn(name="doc_id")
    )
    @MapKeyColumn(name="name")
    private Map<String, PropValue> props = new HashMap<>();

    public Document(String id, String title, String content, Map<String, PropValue> props) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.props = props;
    }

    protected Document() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, PropValue> getProps() {
        return props;
    }

    public void setProps(Map<String, PropValue> props) {
        this.props = props;
    }

    public void setProp(String name, PropValue value) {
        this.props.put(name, value);
    }

    public void removeProp(String name) {
        this.props.remove(name);
    }
}

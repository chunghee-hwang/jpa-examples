package com.example.jpa.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="survey")
public class Survey {
    @Id
    private String id;

    private String name;

    @OneToMany
    @OrderColumn(name = "order_no")
    @JoinColumn(name = "survey_id")
    private List<Question> questions;

    protected Survey() {
    }

    public Survey(String id, String name, List<Question> questions) {
        this.id = id;
        this.name = name;
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void removeQuestion(Question question) {
        this.questions.remove(question);
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}

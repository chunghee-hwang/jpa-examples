package com.example.jpa.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="question")
public class Question {
    @Id
    private String id;
    private String text;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "question_choice",
            joinColumns = @JoinColumn(name="question_id")
    )
    @OrderColumn(name = "idx") // question_choice.idx에 인덱스 자동 저장, 오름차순 정렬
    private List<Choice> choices;

    protected Question() {
    }

    public Question(String id, String text, List<Choice> choices) {
        this.id = id;
        this.text = text;
        this.choices = choices;
    }


    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }




}

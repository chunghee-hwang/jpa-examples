package com.example.jpa.domain;

import jakarta.persistence.*;

@Entity
@Table(name="sight_review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sight_id")
    private Sight sight;

    private Integer grade;
    private String comment;

    protected Review() {
    }

    public Review(Sight sight, Integer grade, String comment) {
        this.sight = sight;
        this.grade = grade;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public Sight getSight() {
        return sight;
    }

    public void setSight(Sight sight) {
        this.sight = sight;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

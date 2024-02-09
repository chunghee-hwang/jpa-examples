package com.example.jpa.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Choice {
    private String text;
    private boolean input;

    protected Choice() {
    }

    public Choice(String text, boolean input) {
        this.text = text;
        this.input = input;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isInput() {
        return input;
    }

    public void setInput(boolean input) {
        this.input = input;
    }
}

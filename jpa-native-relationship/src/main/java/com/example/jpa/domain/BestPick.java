package com.example.jpa.domain;

import jakarta.persistence.*;

// bestpick의 primary key도 email, User의 primary key도 email => 식별자 공유 방식 사용
@Entity
@Table(name="best_pick")
public class BestPick {
    @Id @Column(name = "user_email")
    private String email;

    @OneToOne
    @PrimaryKeyJoinColumn(name="user_email") // 식별자 공유 방식
    private User user;
    private String title;

    protected BestPick() {}

    public BestPick(User user, String title) {
        this.user = user;
        this.title = title;
        this.email = user.getEmail();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

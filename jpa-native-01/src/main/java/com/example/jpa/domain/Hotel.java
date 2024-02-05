package com.example.jpa.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "hotel_info")
public class Hotel {
    @Id
    @Column(name = "hotel_id")
    private String id;

    @Embedded // 엔티티는 아니고, 호텔에 속한 필드를 클래스로 정의하기 위함.. extends와 비슷하려나
    private Address address;

    @Column(name = "nm")
    private String name;

    private int year;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    private LocalDateTime created;

    @Column(name="modified")
    private LocalDateTime lastModified;

    public enum Grade {
        BRONZE,
        SILVER,
        GOLD
    }

    protected Hotel() {
    }

    public Hotel(String id, Address address, String name, int year, Grade grade, LocalDateTime created, LocalDateTime lastModified) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.year = year;
        this.grade = grade;
        this.created = created;
        this.lastModified = lastModified;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public Grade getGrade() {
        return grade;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }
}

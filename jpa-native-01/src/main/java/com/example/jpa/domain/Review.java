package com.example.jpa.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @Column(name= "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hotel_id")
    private String hotelId;

    private Integer mark;

    @Column(name="writer_name")
    private String writerName;

    private String comment;

    protected Review() {
    }

    public Review(String hotelId, Integer mark, String writerName, String comment) {
        this.hotelId = hotelId;
        this.mark = mark;
        this.writerName = writerName;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public String getHotelId() {
        return hotelId;
    }

    public Integer getMark() {
        return mark;
    }

    public String getWriterName() {
        return writerName;
    }

    public String getComment() {
        return comment;
    }
}

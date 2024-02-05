package com.example.jpa.domain;

import jakarta.persistence.*;

// 다른 테이블에 Embeddable을 매핑하는 방법(Embedded를 이용하여 새 테이블 정의)

@Entity
// 첫 번째 방법
@SecondaryTable(name = "writer_intro",
    pkJoinColumns = @PrimaryKeyJoinColumn(
            name = "writer_id", // writer_intro 테이블 컬럼
            referencedColumnName = "id" // writer 테이블 컬럼
    )
)
// 두 번째 방법
@SecondaryTable(name = "writer_address",
    pkJoinColumns = @PrimaryKeyJoinColumn(
            name = "writer_id",
            referencedColumnName = "id"
    )
)
public class Writer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Intro intro;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address1", column = @Column(table = "writer_address", name = "addr1")),
            @AttributeOverride(name = "address2", column = @Column(table = "writer_address", name = "addr2")),
            @AttributeOverride(name = "zipcode", column = @Column(table = "writer_address", name = "zipcode"))
    })
    private Address address;

    protected Writer() {
    }

    public Writer(String name, Intro intro, Address address) {
        this.name = name;
        this.intro = intro;
        this.address = address;
    }

    public Intro getIntro() {
        return intro;
    }

    public Address getAddress() {
        return address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setIntro(Intro intro) {
        this.intro = intro;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Writer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", intro=" + intro +
                ", address=" + address +
                '}';
    }
}

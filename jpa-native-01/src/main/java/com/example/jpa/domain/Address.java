package com.example.jpa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable // 엔티티가 아닌 타입을 한 개 이상의 필드와 매핑할 때 사용
public class Address {
    @Column(name = "addr1")
    private String address1;

    @Column(name = "addr2")
    private String address2;

    @Column(name = "zipcode")
    private String zipcode;

    protected Address(){}

    public Address(String address1, String address2, String zipcode) {
        this.address1 = address1;
        this.address2 = address2;
        this.zipcode = zipcode;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getZipcode() {
        return zipcode;
    }
}

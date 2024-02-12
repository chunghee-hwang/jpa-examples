package com.example.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.Persistable;

import java.time.LocalDateTime;

@Entity
@Table(name="customer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// Persistable: repository.save 함수를 호출했을 때 불필요한 select문이 발생하지 않도록 새로운 엔티티임을 명시하기 위한 인터페이스
public class Customer implements Persistable<String> {
    @Id
    private String email;

    private String name;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Transient // transient: 저장되는 필드가 아님을 명시
    private boolean isNew = true;

    @Override
    public String getId() {
        return this.email;
    }

    @Override
    public boolean isNew() {
        return this.isNew;
    }

    // 저장되기 전에 새로운 엔티티가 아님을 명시
    @PostLoad @PrePersist
    void markNotNew() {
        this.isNew = false;
    }

    public Customer(String email, String name) {
        this.email = email;
        this.name = name;
    }
}

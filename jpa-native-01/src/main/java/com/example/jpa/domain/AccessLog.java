package com.example.jpa.domain;

// 테이블에서 primary key를 가져다 쓰는 방식

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "access_log")
public class AccessLog {
    @Id
    @TableGenerator(
            name = "accessIdGen",
            table = "id_seq", // 실제 테이블 이름
            pkColumnName = "entity",
            pkColumnValue = "access_log",
            valueColumnName = "nextval",
            initialValue = 0,
            allocationSize = 1
    )
    @GeneratedValue(generator = "accessIdGen")
    private Long id;
    private String path;
    private LocalDateTime created;

    protected AccessLog() {
    }

    public AccessLog(String path, LocalDateTime created) {
        this.path = path;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public LocalDateTime getCreated() {
        return created;
    }
}

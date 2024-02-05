package com.example.jpa.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

// 오라클 DB에서 sequence 지정 방법
@Entity
@Table(schema = "crm", name="activity_log")
public class ActivityLog {
    @Id
    @SequenceGenerator(
            name = "log_seq_gen", // 이건 어노테이션에서만 쓰이는 이름
            sequenceName = "activity_seq", // 이게 실제 이름
            schema = "crm",
            allocationSize = 1 // 1이 아니면 여러개 생성되니 주의
    )
    @GeneratedValue(generator = "log_seq_gen")
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name="activity_type")
    private String activityType;

    private LocalDateTime created;

    protected ActivityLog() {
    }

    public ActivityLog(String userId, String activityType, LocalDateTime created) {
        this.userId = userId;
        this.activityType = activityType;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getActivityType() {
        return activityType;
    }

    public LocalDateTime getCreated() {
        return created;
    }
}

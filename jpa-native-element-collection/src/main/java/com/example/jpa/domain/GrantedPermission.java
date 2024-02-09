package com.example.jpa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class GrantedPermission {
    @Column(name="perm")
    private String permission;
    private String grantor;

    protected GrantedPermission() {
    }

    public GrantedPermission(String permission, String grantor) {
        this.permission = permission;
        this.grantor = grantor;
    }

    // HashSet을 이용할 때는 서로 같은 지 판별하기 위해 equals와 hashCode가 같아야 한다.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrantedPermission that = (GrantedPermission) o;
        return Objects.equals(permission, that.permission) && Objects.equals(grantor, that.grantor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permission, grantor);
    }

    @Override
    public String toString() {
        return "GrantedPermission{" +
                "permission='" + permission + '\'' +
                ", grantor='" + grantor + '\'' +
                '}';
    }
}

package com.example.jpa.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    @Id
    private String id;
    private String name;

    // 아래와 같이 1 : N 관계 지정 가능
    @ElementCollection(fetch = FetchType.EAGER) // left fetch join
    @CollectionTable(
            name="role_perm",
            joinColumns = @JoinColumn(name="role_id")
    )
    @Column(name="perm")
    private Set<GrantedPermission> permissions = new HashSet<>();

    protected Role() {
    }

    public Role(String id, String name, Set<GrantedPermission> permissions) {
        this.id = id;
        this.name = name;
        this.permissions = permissions;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<GrantedPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<GrantedPermission> permissions) {
        this.permissions = permissions;
    }

    public void clearPermissions() {
        this.permissions.clear();
    }
}

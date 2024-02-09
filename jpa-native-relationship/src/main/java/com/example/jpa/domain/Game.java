package com.example.jpa.domain;

import jakarta.persistence.*;

import java.util.Map;

@Entity
@Table(name = "game")
public class Game {
    @Id
    private String id;
    private String name;


    @OneToMany
    @JoinColumn(name = "game_id")
    @MapKeyColumn(name = "role_name")
    private Map<String, Member> members;

    protected Game() {
    }

    public Game(String id, String name, Map<String, Member> members) {
        this.id = id;
        this.name = name;
        this.members = members;
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

    public Map<String, Member> getMembers() {
        return members;
    }

    public void setMembers(Map<String, Member> members) {
        this.members = members;
    }

    public void putMember(String roleName, Member member) {
        this.members.put(roleName, member);
    }

    public void removeMember(String roleName) {
        this.members.remove(roleName);
    }
}

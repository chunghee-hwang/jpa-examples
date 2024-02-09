package com.example.jpa.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="team")
public class Team {
    @Id
    private String id;

    private String name;

    @OneToMany
    @JoinColumn(name="team_id")
    private Set<Player> players;

    protected Team() {
    }

    public Team(String id, String name, Set<Player> players) {
        this.id = id;
        this.name = name;
        this.players = players;
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

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    public void removeAllPlayers() {
        this.players.clear();
    }
}

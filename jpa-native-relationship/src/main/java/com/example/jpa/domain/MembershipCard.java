package com.example.jpa.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="membership_card")
public class MembershipCard {
    @Id
    @Column(name="card_no")
    private String cardNumber;

    @OneToOne()
    @JoinColumn(name = "user_email")
    private User owner;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;
    private boolean enabled;

    protected MembershipCard() {
    }

    public MembershipCard(String cardNumber, User owner, LocalDate expiryDate, boolean enabled) {
        this.cardNumber = cardNumber;
        this.owner = owner;
        this.expiryDate = expiryDate;
        this.enabled = enabled;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

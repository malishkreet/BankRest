package com.example.bankcards.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "cards")
public class Card {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number", nullable = false, unique = true, length = 20)
    private String number;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    @Column(nullable = false, length = 3)
    private String cvv;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Card() {}
    public Card(String number, LocalDate expiryDate, String cvv, Account account) {
        this.number = number;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.account = account;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }
    public Account getAccount() { return account; }
    public void setAccount(Account account) { this.account = account; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card c = (Card) o;
        return Objects.equals(id, c.id) && Objects.equals(number, c.number);
    }
    @Override
    public int hashCode() { return Objects.hash(id, number); }
    @Override
    public String toString() { return "Card{id="+id+", number='"+number+"'}"; }
}
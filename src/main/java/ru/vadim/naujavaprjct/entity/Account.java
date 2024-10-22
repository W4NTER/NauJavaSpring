package ru.vadim.naujavaprjct.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "balance")
    private Long balance;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Account() {
    }

    public Account(
            String name,
            Long balance,
            OffsetDateTime createdAt,
            OffsetDateTime updatedAt,
            User user) {
        this.name = name;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }
}

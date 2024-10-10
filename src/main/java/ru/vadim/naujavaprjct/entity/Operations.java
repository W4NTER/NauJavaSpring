package ru.vadim.naujavaprjct.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "operations")
public class Operations {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "sum")
    private Long sum;

    @Column(name = "comment")
    private String comment;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Accounts account;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories category;

    public Operations(Long sum, String comment, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.sum = sum;
        this.comment = comment;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}

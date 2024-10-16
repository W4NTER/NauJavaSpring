package ru.vadim.naujavaprjct.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "categories")
public class Categories {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "category_type", unique = true)
    private String type;

    @Column(name = "title")
    private String title;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @OneToMany(mappedBy = "category")
    private Set<Operations> operationsSet;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public Categories(
            String type,
            String title,
            OffsetDateTime createdAt,
            OffsetDateTime updatedAt,
            Users user
    ) {
        this.type = type;
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }
}

package ru.vadim.naujavaprjct.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    public Users(String username, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.username = username;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Accounts> accountsSet;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Categories> categories;

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

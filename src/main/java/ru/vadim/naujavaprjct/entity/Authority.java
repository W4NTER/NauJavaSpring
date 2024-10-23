package ru.vadim.naujavaprjct.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "authority")
@RequiredArgsConstructor
public class Authority {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "authority")
    private String authority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Authority(String authority, User user) {
        this.authority = authority;
        this.user = user;
    }
}

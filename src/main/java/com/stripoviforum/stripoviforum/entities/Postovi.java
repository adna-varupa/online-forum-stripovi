package com.stripoviforum.stripoviforum.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "postovi")
public class Postovi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Korisnici korisnik;

    @ManyToOne
    @JoinColumn(name = "comic_id", nullable = false)
    private Stripovi stripovi;

    public void setStripovi(Stripovi stripovi) {
        this.stripovi = stripovi;
    }

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}

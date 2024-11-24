package com.stripoviforum.stripoviforum.entities;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class Postovi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "korisnik_id")
    private Korisnici korisnik;
    public Korisnici getKorisnik() {
        return korisnik;
    }
    public void setKorisnik(Korisnici korisnik) {
        this.korisnik = korisnik;
    }
    @ManyToOne
    @JoinColumn(name = "comic_id")
    private Stripovi stripovi;  // This connects the post to a comic

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Stripovi getStripovi() {
        return stripovi;
    }

    public void setStripovi(Stripovi stripovi) {
        this.stripovi = stripovi;
    }

    private LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
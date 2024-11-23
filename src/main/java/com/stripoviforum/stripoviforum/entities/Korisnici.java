package com.stripoviforum.stripoviforum.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "korisnici")
public class Korisnici {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 10)
    private String role;

    @ManyToMany
    @JoinTable(
            name = "user_comics",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "comic_id")
    )
    private List<Stripovi> comics = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Stripovi> getComics() {
        return comics;
    }

    public void setComics(List<Stripovi> comics) {
        this.comics = comics;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

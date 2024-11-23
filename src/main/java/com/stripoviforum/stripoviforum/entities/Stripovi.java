package com.stripoviforum.stripoviforum.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "stripovi")
public class Stripovi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, length = 50)
    private String author;

    @ManyToMany(mappedBy = "comics")
    private List<Korisnici> users;

    public Long getId() {
        return id;
    }
}

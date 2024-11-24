package com.stripoviforum.stripoviforum.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Stripovi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "stripovi")
    private List<Postovi> posts;  // A comic can have multiple posts

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Postovi> getPosts() {
        return posts;
    }

    public void setPosts(List<Postovi> posts) {
        this.posts = posts;
    }
}

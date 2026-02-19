package com.ticketworld.Ticketworld.entity;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column
    private String musicGenre;

    @Column
    private String biography;

    @ManyToMany(mappedBy = "artists")
    @ToString.Exclude
    private Set<Event> events = new HashSet<>();

    //Constructor sin parámetros
    public Artist(){

    }

    //Constructor con parámetros
    public Artist(Long id, String name, String lastName, String musicGenre, String biography, Set<Event> events){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.musicGenre = musicGenre;
        this.biography = biography;
        this.events = events;
    }

    //Getters and Setters


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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMusicGenre() {
        return musicGenre;
    }

    public void setMusicGenre(String musicGenre) {
        this.musicGenre = musicGenre;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}

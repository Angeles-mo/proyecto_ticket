package com.ticketworld.Ticketworld.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ticketworld.Ticketworld.dto.ArtistDTO;
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

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @JsonIgnore
    private Account account;

    //Constructor sin parámetros
    public Artist(){

    }

    //Constructor con parámetros
    public Artist(Long id, String name, String lastName, String musicGenre, String biography, Set<Event> events, Account account){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.musicGenre = musicGenre;
        this.biography = biography;
        this.events = events;
        this.account = account;
    }

    //Pasar de DTO a entidad
    public Artist(ArtistDTO artistDTO){
        this.id = artistDTO.getId();
        this.name = artistDTO.getName();
        this.lastName = artistDTO.getLastName();
        this.musicGenre = artistDTO.getMusicGenre();
        this.biography = artistDTO.getBiography();
    }

    //Pasar de una entidad a una DTO
    public static ArtistDTO toDTO(Artist artist){
        if (artist == null){
            return null;
        }
        return new ArtistDTO(
                artist.getId(),
                artist.getName(),
                artist.getLastName(),
                artist.getMusicGenre(),
                artist.getBiography()
        );
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

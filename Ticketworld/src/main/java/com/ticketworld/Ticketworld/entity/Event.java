package com.ticketworld.Ticketworld.entity;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date dateStartTime;

    @Column(nullable = false)
    private Date dateEndTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany
    @JoinTable(name = "event_artist", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "artist_id"))
    @ToString.Exclude
    private Set<Artist> artists = new HashSet<>();

    @OneToMany(mappedBy = "ticketType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketType> ticketTypes;

    //Constructores sin parámetros
    public Event(){

    }

    //Constructores con parámetros
    public Event(Long id, Place place, String title, String description, Date dateStartTime, Date dateEndTime, Status status, Set<Artist> artists, List<TicketType> ticketTypes){
        this.id = id;
        this.place = place;
        this.title = title;
        this.description = description;
        this.dateStartTime = dateStartTime;
        this.dateEndTime = dateEndTime;
        this.status = status;
        this.artists = artists;
        this.ticketTypes = ticketTypes;
    }

    //Getter and Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateStartTime() {
        return dateStartTime;
    }

    public void setDateStartTime(Date dateStartTime) {
        this.dateStartTime = dateStartTime;
    }

    public Date getDateEndTime() {
        return dateEndTime;
    }

    public void setDateEndTime(Date dateEndTime) {
        this.dateEndTime = dateEndTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public List<TicketType> getTicketTypes() {
        return ticketTypes;
    }

    public void setTicketTypes(List<TicketType> ticketTypes) {
        this.ticketTypes = ticketTypes;
    }
}

package com.ticketworld.Ticketworld.dto;

import com.ticketworld.Ticketworld.entity.Artist;
import com.ticketworld.Ticketworld.entity.Place;
import com.ticketworld.Ticketworld.entity.Status;
import com.ticketworld.Ticketworld.entity.TicketType;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EventDTO {

    private Long id;
    private Place place;
    private String title;
    private String description;
    private Date dateStartTime;
    private Date dateEndTime;
    private Status status;
    private Set<Artist> artists = new HashSet<>();
    private List<TicketType> ticketTypes;

    //Constructores sin parámetros
    public EventDTO(){

    }

    //Constructores con parámetros
    public EventDTO(Long id, Place place, String title, String description, Date dateStartTime, Date dateEndTime, Status status, Set<Artist> artists, List<TicketType> ticketTypes){
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

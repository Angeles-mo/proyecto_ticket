package com.ticketworld.Ticketworld.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String province;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private Integer totalCapacity;

    @Column
    private String seatMap;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events;

    // Constructor sin parámetros
    public Place(){

    }

    //Constructor con parámetros
    public Place(Long id, String name, String address, String city, String province, String country, Integer totalCapacity, String seatMap, List<Event> events){
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.province = province;
        this.country = country;
        this.totalCapacity = totalCapacity;
        this.seatMap = seatMap;
        this.events = events;
    }

    //Getter and Setter

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(Integer totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public String getSeatMap() {
        return seatMap;
    }

    public void setSeatMap(String seatMap) {
        this.seatMap = seatMap;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}

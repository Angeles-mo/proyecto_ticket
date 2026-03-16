package com.ticketworld.Ticketworld.dto;

import com.ticketworld.Ticketworld.entity.Event;

import java.util.List;

public class PlaceDTO {

    private Long id;
    private String name;
    private String address;
    private String city;
    private String province;
    private String country;
    private Integer totalCapacity;
    private String seatMap;
    private List<Event> events;

    // Constructor sin parámetros
    public PlaceDTO(){

    }

    //Constructor con parámetros
    public PlaceDTO(Long id, String name, String address, String city, String province, String country, Integer totalCapacity, String seatMap, List<Event> events){
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

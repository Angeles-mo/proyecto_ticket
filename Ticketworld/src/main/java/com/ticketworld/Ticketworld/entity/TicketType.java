package com.ticketworld.Ticketworld.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class TicketType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer capacity;

    @OneToMany(mappedBy = "singleTickets", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SingleTicket> singleTickets;

    //Constructores sin parámetros
    public TicketType(){

    }

    //Constructores con parámetros
    public TicketType(Long id, Event event, String name, Double price, Integer capacity, List<SingleTicket> singleTickets){
        this.id = id;
        this.event = event;
        this.name = name;
        this.price = price;
        this.capacity = capacity;
        this.singleTickets = singleTickets;
    }

    //Getter and setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<SingleTicket> getSingleTickets() {
        return singleTickets;
    }

    public void setSingleTickets(List<SingleTicket> singleTickets) {
        this.singleTickets = singleTickets;
    }
}

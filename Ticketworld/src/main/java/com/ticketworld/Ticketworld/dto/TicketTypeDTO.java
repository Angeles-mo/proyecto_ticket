package com.ticketworld.Ticketworld.dto;

import com.ticketworld.Ticketworld.entity.Event;
import com.ticketworld.Ticketworld.entity.SingleTicket;

import java.util.List;

public class TicketTypeDTO {

    private Long id;
    private Event event;
    private String name;
    private Double price;
    private Integer capacity;
    private List<SingleTicket> singleTickets;

    //Constructores sin parámetros
    public TicketTypeDTO(){

    }

    //Constructores con parámetros
    public TicketTypeDTO(Long id, Event event, String name, Double price, Integer capacity, List<SingleTicket> singleTickets){
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

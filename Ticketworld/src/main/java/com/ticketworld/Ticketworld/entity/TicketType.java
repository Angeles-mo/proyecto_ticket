package com.ticketworld.Ticketworld.entity;

import com.ticketworld.Ticketworld.dto.TicketTypeDTO;
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

    @OneToMany(mappedBy = "ticketType", cascade = CascadeType.ALL, orphanRemoval = true)
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

    //Pasar de DTO a entidad
    public TicketType(TicketTypeDTO ticketTypeDTO){
        this.id = ticketTypeDTO.getId();
        this.event = ticketTypeDTO.getEvent();
        this.name = ticketTypeDTO.getName();
        this.price = ticketTypeDTO.getPrice();
        this.capacity = ticketTypeDTO.getCapacity();
        this.singleTickets = ticketTypeDTO.getSingleTickets();
    }

    //Pasar de entidad a una DTO
    public static TicketTypeDTO toDTO(TicketType ticketType){
        if (ticketType == null){
            return null;
        }
        return new TicketTypeDTO(
                ticketType.getId(),
                ticketType.getEvent(),
                ticketType.getName(),
                ticketType.getPrice(),
                ticketType.getCapacity(),
                ticketType.getSingleTickets()
        );
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

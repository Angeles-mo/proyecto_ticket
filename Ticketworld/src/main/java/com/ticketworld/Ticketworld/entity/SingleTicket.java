package com.ticketworld.Ticketworld.entity;

import com.ticketworld.Ticketworld.dto.SingleTicketDTO;
import jakarta.persistence.*;

@Entity
public class SingleTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticketType_id", nullable = false)
    private TicketType ticketType;

    @Column(name = "seat_row", nullable = false)
    private Integer row;

    @Column(nullable = false)
    private Integer seatNumber;

    @Column(nullable = false)
    private String ticketCode;

    @Column(nullable = false)
    private Double ticketPrice;

    @Column
    @Enumerated(EnumType.STRING)
    private State state;

    //Constructores sin parámetros
    public SingleTicket(){

    }

    //Constructores con parámetros
    public SingleTicket(Long id, TicketType tickettype, Integer row, Integer seatNumber, String ticketCode, Double ticketPrice, State state){
        this.id = id;
        this.ticketType = tickettype;
        this.row = row;
        this.seatNumber = seatNumber;
        this.ticketCode = ticketCode;
        this.ticketPrice = ticketPrice;
        this.state = state;
    }

    //Pasar de DTO a entidad
    public SingleTicket(SingleTicketDTO singleTicketDTO){
        this.id = singleTicketDTO.getId();
        this.ticketType = singleTicketDTO.getTicketType();
        this.row = singleTicketDTO.getRow();
        this.seatNumber = singleTicketDTO.getSeatNumber();
        this.ticketCode = singleTicketDTO.getTicketCode();
        this.ticketPrice = singleTicketDTO.getTicketPrice();
        this.state = singleTicketDTO.getState();
    }

    //Pasar de entidad a DTO
    public static SingleTicketDTO toDTO(SingleTicket singleTicket){
        if (singleTicket == null){
            return null;
        }return new SingleTicketDTO(
                singleTicket.getId(),
                singleTicket.getTicketType(),
                singleTicket.getRow(),
                singleTicket.getSeatNumber(),
                singleTicket.getTicketCode(),
                singleTicket.getTicketPrice(),
                singleTicket.getState()
        );
    }

    //Getter and Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

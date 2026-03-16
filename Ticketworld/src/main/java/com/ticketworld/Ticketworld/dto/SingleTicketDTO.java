package com.ticketworld.Ticketworld.dto;

import com.ticketworld.Ticketworld.entity.State;
import com.ticketworld.Ticketworld.entity.TicketType;

public class SingleTicketDTO {

    private Long id;
    private TicketType ticketType;
    private Integer row;
    private Integer seatNumber;
    private String ticketCode;
    private Double ticketPrice;
    private State state;


    //Constructores sin parámetros
    public SingleTicketDTO(){

    }

    //Constructores con parámetros
    public SingleTicketDTO(Long id, TicketType tickettype, Integer row, Integer seatNumber, String ticketCode, Double ticketPrice, State state){
        this.id = id;
        this.ticketType = tickettype;
        this.row = row;
        this.seatNumber = seatNumber;
        this.ticketCode = ticketCode;
        this.ticketPrice = ticketPrice;
        this.state = state;
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

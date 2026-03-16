package com.ticketworld.Ticketworld.dto;

import com.ticketworld.Ticketworld.entity.Payment;
import com.ticketworld.Ticketworld.entity.PurchaseState;
import com.ticketworld.Ticketworld.entity.User;

import java.util.Date;

public class OrderDTO {

    private Long id;
    private User user;
    private Date purchaseDate;
    private Double total;
    private PurchaseState purchasestate;
    private Payment payment;

    //Constructores sin parámetros
    public OrderDTO(){

    }

    //Constructores con parámetros
    public OrderDTO(Long id, User user, Date purchaseDate, Double total, PurchaseState purchasestate, Payment payment){
        this.id = id;
        this.user = user;
        this.purchaseDate = purchaseDate;
        this.total = total;
        this.purchasestate = purchasestate;
        this.payment = payment;
    }

    //Getter and Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public PurchaseState getPurchasestate() {
        return purchasestate;
    }

    public void setPurchasestate(PurchaseState purchasestate) {
        this.purchasestate = purchasestate;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}

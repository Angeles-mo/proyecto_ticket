package com.ticketworld.Ticketworld.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Date purchaseDate;

    @Column(nullable = false)
    private Double total;

    @Enumerated(EnumType.STRING)
    private PurchaseState purchasestate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    //Constructores sin parámetros
    public Order(){

    }

    //Constructores con parámetros
    public Order(Long id, User user, Date purchaseDate, Double total, PurchaseState purchasestate, Payment payment){
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

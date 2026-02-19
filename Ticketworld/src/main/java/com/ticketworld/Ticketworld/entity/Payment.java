package com.ticketworld.Ticketworld.entity;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.Date;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "payment")
    @ToString.Exclude
    private Order order;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private Integer transactionId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentstatus;

    @Column(nullable = false)
    private Date paymentDate;

    //Constructores sin parámetros
    public Payment(){

    }

    //Constructores con parámetros
    public Payment(Long id, Order order, String paymentMethod, Integer transactionId, PaymentStatus paymentstatus, Date paymentDate){
        this.id = id;
        this.order = order;
        this.paymentMethod = paymentMethod;
        this.transactionId = transactionId;
        this.paymentstatus = paymentstatus;
        this.paymentDate = paymentDate;
    }

    //Getter and Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public PaymentStatus getPaymentstatus() {
        return paymentstatus;
    }

    public void setPaymentstatus(PaymentStatus paymentstatus) {
        this.paymentstatus = paymentstatus;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}

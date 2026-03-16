package com.ticketworld.Ticketworld.dto;

import com.ticketworld.Ticketworld.entity.Order;
import com.ticketworld.Ticketworld.entity.PaymentStatus;

import java.util.Date;

public class PaymentDTO {

    private Long id;
    private Order order;
    private String paymentMethod;
    private Integer transactionId;
    private PaymentStatus paymentstatus;
    private Date paymentDate;

    //Constructores sin parámetros
    public PaymentDTO(){

    }

    //Constructores con parámetros
    public PaymentDTO(Long id, Order order, String paymentMethod, Integer transactionId, PaymentStatus paymentstatus, Date paymentDate){
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

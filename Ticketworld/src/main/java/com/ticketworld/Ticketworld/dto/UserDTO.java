package com.ticketworld.Ticketworld.dto;

import com.ticketworld.Ticketworld.entity.Account;
import com.ticketworld.Ticketworld.entity.Order;

import java.util.List;

public class UserDTO {

    private Long id;
    private String name;
    private String lastName;
    private String phoneNumber;
    private List<Order> orders;
    private Account account;

    // Constructor sin parámetros
    public UserDTO(){

    }

    // Constructor con parámetros
    public UserDTO(Long id, String name, String lastName, String phoneNumber, List<Order> orders, Account account){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.orders = orders;
        this.account = account;
    }

    // Getter (nos coge lo que pedimos) y setter (mete lo que le pedimos)

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
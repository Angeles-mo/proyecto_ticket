package com.ticketworld.Ticketworld.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ticketworld.Ticketworld.dto.UserDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @JsonIgnore
    private Account account;

    // Constructor sin parámetros
    public User(){

    }

    // Constructor con parámetros
    public User(Long id, String name, String lastName, String phoneNumber, List<Order> orders, Account account){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.orders = orders;
        this.account = account;
    }

    // Pasar de DTO a entidad
    public User(UserDTO userDTO){
        this.id = userDTO.getId();
        this.name = userDTO.getName();
        this.lastName = userDTO.getLastName();
        this.phoneNumber = userDTO.getPhoneNumber();
        this.orders = userDTO.getOrders();
        this.account = userDTO.getAccount();
    }

    // Pasar de una entidad a una DTO
    public static UserDTO toDTO(User user){
        if (user == null){
            return null;
        }
        // Return de la creación del nuevo DTO
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getOrders(),
                user.getAccount()
        );
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
package com.ticketworld.Ticketworld.dto;


public class UserDTO {

    private Long id;
    private String name;
    private String lastName;
    private String phoneNumber;

    // Constructor sin parámetros
    public UserDTO(){

    }

    // Constructor con parámetros
    public UserDTO(Long id, String name, String lastName, String phoneNumber){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
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
}
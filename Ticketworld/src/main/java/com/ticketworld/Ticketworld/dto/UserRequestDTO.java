package com.ticketworld.Ticketworld.dto;

public class UserRequestDTO {

    private UserDTO user;

    // Constructor sin parámetros
    public UserRequestDTO() {
    }

    // Constructor con parámetros
    public UserRequestDTO(UserDTO user) {
        this.user = user;
    }

    // Getters y Setters

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}

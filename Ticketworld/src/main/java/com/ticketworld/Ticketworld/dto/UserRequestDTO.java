package com.ticketworld.Ticketworld.dto;

public class UserRequestDTO {

    private UserDTO user;
    private AccountDTO account;

    // Constructor sin parámetros
    public UserRequestDTO() {
    }

    // Constructor con parámetros
    public UserRequestDTO(UserDTO user, AccountDTO account) {
        this.user = user;
        this.account = account;
    }

    // Getters y Setters

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }
}

package com.ticketworld.Ticketworld.dto;

import com.ticketworld.Ticketworld.entity.Artist;
import com.ticketworld.Ticketworld.entity.Rol;
import com.ticketworld.Ticketworld.entity.User;

public class AccountDTO {

    private Long id;
    private String email;
    private String password;
    private Rol role;
    private User user;
    private Artist artist;

    //Constructor sin parámetros
    public AccountDTO(){

    }

    //Constructor con parámetros
    public AccountDTO(Long id, String email, String password, Rol role, User user, Artist artist){
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.user = user;
        this.artist = artist;
    }

    //Getter y setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRole() {
        return role;
    }

    public void setRole(Rol role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}

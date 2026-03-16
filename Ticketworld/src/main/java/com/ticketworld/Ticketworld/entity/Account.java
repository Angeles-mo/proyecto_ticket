package com.ticketworld.Ticketworld.entity;

import com.ticketworld.Ticketworld.dto.AccountDTO;
import jakarta.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol role;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Artist artist;

    //Constructor sin parámetros
    public Account(){

    }

    //Constructor con parámetros
    public Account(Long id, String email, String password, Rol role, User user, Artist artist){
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.user = user;
        this.artist = artist;
    }

    //Pasar de DTO a entidad
    public Account(AccountDTO accountDTO){
        this.id = accountDTO.getId();
        this.email = accountDTO.getEmail();
        this.password = accountDTO.getPassword();
        this.role = accountDTO.getRole();
        this.user = accountDTO.getUser();
        this.artist = accountDTO.getArtist();
    }

    //Pasar de entidad a DTO
    public static AccountDTO toDTO(Account account){
        if (account == null){
            return null;
        }
        return new AccountDTO(
                account.getId(),
                account.getEmail(),
                account.getPassword(),
                account.getRole(),
                account.getUser(),
                account.getArtist()
        );
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

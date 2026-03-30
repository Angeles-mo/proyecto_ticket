package com.ticketworld.Ticketworld.dto;

public class ArtistRequestDTO {

    private ArtistDTO artist;
    private AccountDTO account;

    // Constructor sin parámetros
    public ArtistRequestDTO() {
    }

    // Constructor con parámetros
    public ArtistRequestDTO(ArtistDTO artist, AccountDTO account) {
        this.artist = artist;
        this.account = account;
    }

    // Getters y Setters

    public ArtistDTO getArtist() {
        return artist;
    }

    public void setArtist(ArtistDTO artist) {
        this.artist = artist;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }
}

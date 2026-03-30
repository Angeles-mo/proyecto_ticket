package com.ticketworld.Ticketworld.dto;

public class ArtistRequestDTO {

    private ArtistDTO artist;

    // Constructor sin parámetros
    public ArtistRequestDTO() {
    }

    // Constructor con parámetros
    public ArtistRequestDTO(ArtistDTO artist) {
        this.artist = artist;
    }

    // Getters y Setters

    public ArtistDTO getArtist() {
        return artist;
    }

    public void setArtist(ArtistDTO artist) {
        this.artist = artist;
    }
}

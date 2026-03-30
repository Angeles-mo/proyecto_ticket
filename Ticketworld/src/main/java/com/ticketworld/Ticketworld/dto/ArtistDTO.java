package com.ticketworld.Ticketworld.dto;

public class ArtistDTO {

    private Long id;
    private String name;
    private String lastName;
    private String musicGenre;
    private String biography;

    //Constructor sin parámetros
    public ArtistDTO(){
    }

    //Constructor con parámetros
    public ArtistDTO(Long id, String name, String lastName, String musicGenre, String biography){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.musicGenre = musicGenre;
        this.biography = biography;
    }

    //Getters and Setters

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

    public String getMusicGenre() {
        return musicGenre;
    }

    public void setMusicGenre(String musicGenre) {
        this.musicGenre = musicGenre;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}

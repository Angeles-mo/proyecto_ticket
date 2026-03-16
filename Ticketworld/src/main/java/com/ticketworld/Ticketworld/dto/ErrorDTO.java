package com.ticketworld.Ticketworld.dto;

import lombok.Value;

@Value
public class ErrorDTO {

    //Aviso del Error
    public ErrorDTO(String string){
        this.error = string;
    }
    String error;
}

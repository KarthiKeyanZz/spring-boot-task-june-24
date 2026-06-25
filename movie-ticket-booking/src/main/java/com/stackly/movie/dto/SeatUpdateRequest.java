package com.stackly.movie.dto;

import com.stackly.movie.validation.SeatNumber;
import jakarta.validation.constraints.NotBlank;

public class SeatUpdateRequest {

    @NotBlank(message = "Seat number is required")
    @SeatNumber
    private String seatNumber;

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}

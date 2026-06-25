package com.stackly.movie.exception;

public class SeatAlreadyBookedException extends RuntimeException {

    public SeatAlreadyBookedException(String seatNumber, String movieName) {
        super("Seat " + seatNumber + " is already booked for " + movieName);
    }
}

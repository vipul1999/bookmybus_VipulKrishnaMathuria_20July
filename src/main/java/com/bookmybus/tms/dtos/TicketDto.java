package com.bookmybus.tms.dtos;

import com.bookmybus.tms.enums.TicketStatus;

import java.util.Date;

public class TicketDto {
    private Long ticketId;
    private String busNumber;
    private int seatNumber;
    private Date bookingTime;
    private TicketStatus ticketStatus;

    // Constructors, getters, and setters
    public TicketDto() {}

    public TicketDto(Long ticketId, String busNumber, int seatNumber, Date bookingTime, TicketStatus ticketStatus) {
        this.ticketId = ticketId;
        this.busNumber = busNumber;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.ticketStatus = ticketStatus;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}


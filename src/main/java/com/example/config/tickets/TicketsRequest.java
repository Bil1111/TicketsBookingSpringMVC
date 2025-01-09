package com.example.config.tickets;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TicketsRequest {
    @NotBlank(message = "Row is required")
    private String row;
    @NotBlank(message = "Place is required")
    private String place;
    @NotNull(message = "Concert ID is required")
    private Long concert;
    @NotNull(message = "Customer ID is required")
    private Long customer;

    public TicketsRequest(String row, String place, Long concert, Long customer) {
        this.row = row;
        this.place = place;
        this.concert = concert;
        this.customer = customer;
    }
    public TicketsRequest() {

    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Long getConcert() {
        return concert;
    }

    public void setConcert(Long concert) {
        this.concert = concert;
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }
}

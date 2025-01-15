package com.example.config.customers;

import com.example.config.tickets.Ticket;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Invalid email format")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String name;

    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be in a valid format (e.g. +1234567890)")
    private String phoneNumber;

    @NotEmpty(message = "Login cannot be empty")
    @Size(min = 4, max = 50, message = "Login should be between 4 and 50 characters")
    private String login;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, max = 100, message = "Password should be between 6 and 100 characters")
    private String password;
    @Column(unique = true)
    private String authToken;
    @Enumerated(EnumType.STRING) // Зберігаємо роль як текст у БД
    private Role role;
    @JsonManagedReference
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    public Customer(String email, String name, String phoneNumber, String login, String password,String authToken, Role role) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
        this.authToken=authToken;
        this.role=role;
    }

    public Customer() {

    }
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

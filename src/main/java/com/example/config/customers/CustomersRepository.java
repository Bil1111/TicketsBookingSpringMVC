package com.example.config.customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<Customer,Long> {
    Customer findByEmail(String email);

    Customer findByLogin(String login);
}

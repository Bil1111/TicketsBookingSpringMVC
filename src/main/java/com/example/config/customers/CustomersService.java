package com.example.config.customers;

import com.example.config.token.JwtTokenProvider;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomersService {
    @Autowired
    private CustomersRepository customersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public void addCustomer(CustomersRegistrationRequest request) {
        Customer customer = new Customer(
                request.getEmail(),
                request.getName(),
                request.getPhoneNumber(),
                request.getLogin(),
                passwordEncoder.encode(request.getPassword()),
                jwtTokenProvider.generateToken(request.getEmail()));
        customersRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customersRepository.deleteById(id);
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customersRepository.findById(id);
    }

    public List<Customer> getAllCustomers() {
        return customersRepository.findAll();
    }

    public void updateCustomerDetails(CustomersRegistrationRequest request, Long customerId) {
        Optional<Customer> customerOptional = customersRepository.findById(customerId);

        customerOptional.ifPresentOrElse(customer -> {
            if (StringUtils.isNotBlank(request.getName())) {
                customer.setName(request.getName());
            }
            if (StringUtils.isNotBlank(request.getEmail())) {
                customer.setEmail(request.getEmail());
            }
            if (StringUtils.isNotBlank(request.getPhoneNumber())) {
                customer.setPhoneNumber(request.getPhoneNumber());
            }
            if (StringUtils.isNotBlank(request.getLogin())) {
                customer.setLogin(request.getLogin());
            }
            if (StringUtils.isNotBlank(request.getPassword())) {
                customer.setPassword(request.getPassword());
            }
            customersRepository.save(customer);
        }, () -> {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Customer with id " + customerId + " not found"
            );
        });
    }

    public boolean loginUser(String email, String password) {
        Customer customer = customersRepository.findByEmail(email);
        if (customer == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Customer with email " + email + " not found"
            );
        }
        boolean isPasswordValid = passwordEncoder.matches(password, customer.getPassword());
        if (isPasswordValid) {
            // Генерація токену після перевірки паролю
            String token = jwtTokenProvider.generateToken(email);
            customer.setAuthToken(token);  // Оновлення токену для користувача
            customersRepository.save(customer); // Зберігаємо оновленого користувача
        }
        return isPasswordValid;
    }

    public Optional<Customer> findByEmail(String email) {
        return Optional.ofNullable(customersRepository.findByEmail(email));
    }

    public Optional<Customer> findByLogin(String login) {
        return Optional.ofNullable(customersRepository.findByLogin(login));
    }
}

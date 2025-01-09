package com.example.config.customers;

import com.example.config.token.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomersController {

    @Autowired
    private CustomersService customersService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/add")
    public ResponseEntity<String> addCustomer(@RequestBody CustomersRegistrationRequest request) {
        // Перевірка, чи вже існує користувач з таким логіном
        Optional<Customer> existingCustomer = customersService.findByLogin(request.getLogin());

        if (existingCustomer.isPresent()) {
            // Якщо користувач вже є, повертаємо помилку
            return new ResponseEntity<>("User with this login already exists", HttpStatus.CONFLICT);
        }

        // Якщо користувача з таким логіном немає, додаємо нового
        customersService.addCustomer(request);

        return new ResponseEntity<>("Customer added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
        Optional<Customer> customer = customersService.getCustomerById(id);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customersService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {
        customersService.deleteCustomer(id);
        return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCustomerDetails(@RequestBody CustomersRegistrationRequest request, @PathVariable("id") Long id) {
        customersService.updateCustomerDetails(request, id);
        return new ResponseEntity<>("Customer updated successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginCustomer(@RequestBody CustomerLoginRequest request) {
        Optional<Customer> customer = customersService.findByEmail(request.getEmail());

        if (customer.isPresent() && customersService.loginUser(request.getEmail(), request.getPassword())) {
            // Генерація токену після успішного входу
            String token = jwtTokenProvider.generateToken(request.getEmail()); // Генерація токену
            Map<String, String> response = new HashMap<>();
            response.put("token", token); // Повертаємо токен у відповіді
            return new ResponseEntity<>(response, HttpStatus.OK); // Повертаємо ResponseEntity з Map
        } else {
            Map<String, String> errorResponse = new HashMap<>();

            // Якщо користувача не знайдено
            if (!customer.isPresent()) {
                errorResponse.put("error", "User not found");
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }

            // Якщо неправильний пароль
            errorResponse.put("error", "Invalid password");
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }
    }

}

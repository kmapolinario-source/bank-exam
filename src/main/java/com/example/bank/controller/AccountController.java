package com.example.bank.controller;

import com.example.bank.dto.CreateAccountRequest;
import com.example.bank.dto.CreateAccountResponse;
import com.example.bank.dto.CustomerInquiryResponse;
import com.example.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    AccountService service;

    @PostMapping
    public ResponseEntity<CreateAccountResponse> create(@Valid @RequestBody CreateAccountRequest req) {
        return new ResponseEntity<>(service.createAccount(req), HttpStatus.CREATED);
    }

    @GetMapping("/{customerNumber}")
    public ResponseEntity<CustomerInquiryResponse> getCustomer(@PathVariable Long customerNumber) {

        return new ResponseEntity<>(
                service.getCustomer(customerNumber),
                HttpStatus.FOUND
        );
    }
}

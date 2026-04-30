package com.example.bank.service;

import com.example.bank.dto.AccountInfo;
import com.example.bank.dto.CreateAccountRequest;
import com.example.bank.dto.CreateAccountResponse;
import com.example.bank.dto.CustomerInquiryResponse;
import com.example.bank.entity.Account;
import com.example.bank.entity.Customer;
import com.example.bank.exception.CustomerNotFoundException;
import com.example.bank.repository.AccountRepository;
import com.example.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AccountRepository accountRepository;

    public CreateAccountResponse createAccount(CreateAccountRequest req) {

        Customer c = new Customer();
        c.setCustomerNumber(System.currentTimeMillis());
        c.setCustomerName(req.customerName);
        c.setCustomerMobile(req.customerMobile);
        c.setCustomerEmail(req.customerEmail);
        c.setAddress1(req.address1);
        c.setAddress2(req.address2);

        customerRepository.save(c);

        Account a = new Account();
        a.setAccountNumber(System.currentTimeMillis());
        a.setAccountType(req.accountType);
        a.setAvailableBalance(0.0);
        a.setCustomer(c);

        accountRepository.save(a);

        CreateAccountResponse res = new CreateAccountResponse();
        res.customerNumber = c.getCustomerNumber();
        res.transactionStatusCode = 201;
        res.transactionStatusDescription = "Customer account created";

        return res;
    }

    public CustomerInquiryResponse getCustomer(Long customerNumber) {

        Customer customer = customerRepository.findByCustomerNumber(customerNumber)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        List<Account> accounts = accountRepository.findByCustomer(customer);

        List<AccountInfo> savings = accounts.stream().map(acc -> {
            AccountInfo info = new AccountInfo();
            info.setAccountNumber(acc.getAccountNumber());
            info.setAccountType(acc.getAccountType());
            info.setAvailableBalance(acc.getAvailableBalance());
            return info;
        }).collect(Collectors.toList());

        CustomerInquiryResponse res = new CustomerInquiryResponse();
        res.setCustomerNumber(customer.getCustomerNumber());
        res.setCustomerName(customer.getCustomerName());
        res.setCustomerMobile(customer.getCustomerMobile());
        res.setCustomerEmail(customer.getCustomerEmail());
        res.setAddress1(customer.getAddress1());
        res.setAddress2(customer.getAddress2());

        res.setSavings(savings);
        res.setTransactionStatusCode(302);
        res.setTransactionStatusDescription("Customer Account found");

        return res;
    }
}

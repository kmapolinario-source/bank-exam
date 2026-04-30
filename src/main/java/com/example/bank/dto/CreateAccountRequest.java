package com.example.bank.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CreateAccountRequest {

    @NotBlank(message = "Customer name is required field")
    public String customerName;

    @NotBlank(message = "Customer mobile is required field")
    public String customerMobile;

    @NotBlank(message = "Email is required field")
    @Email
    public String customerEmail;

    @NotBlank(message = "Address1 is required field")
    public String address1;

    public String address2;

    @NotBlank(message = "Account type is required field")
    public String accountType;
}
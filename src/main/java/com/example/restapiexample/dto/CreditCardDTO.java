package com.example.restapiexample.dto;

import com.example.restapiexample.enums.Gender;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class CreditCardDTO {
    @NotNull
    @Size(min = 16, max = 16)
    private String cardNumber;
    @NotNull
    private String userName;
    @NotNull
    private LocalDate userBirthday;
    @NotNull
    private Gender userGender;
    @NotNull
    private String userAddress;


}


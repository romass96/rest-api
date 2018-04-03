package com.example.restapiexample.model;

import com.example.restapiexample.enums.Gender;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @NotBlank
    private String name;

    @Column(name = "password", nullable = false)
    @NotBlank
    private String password;

    @Column(name = "birthday", nullable = false)
    @NotNull
    private LocalDate birthday;

    @Column(name = "gender", nullable = false)
    @Enumerated(value = EnumType.STRING)
    @NotNull
    private Gender gender;

    @Column(name = "cardNumber", nullable = false, length = 16)
    private String cardNumber;

    @Column(name = "balance", nullable = false, columnDefinition = "Decimal(10,2) default '0.00'")
    private Float balance;

    @Column(name = "address", nullable = false)
    private String address;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

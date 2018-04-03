package com.example.restapiexample.controller;


import com.example.restapiexample.dto.AuthDTO;
import com.example.restapiexample.dto.CreditCardDTO;
import com.example.restapiexample.dto.SendRequestDTO;
import com.example.restapiexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private UserService userService;
    
    @PostMapping(path = "/new_card")
    public ResponseEntity createCard(@RequestBody CreditCardDTO creditCardDTO) {
        if (userService.isCardExist(creditCardDTO.getCardNumber())) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(userService.save(creditCardDTO), HttpStatus.OK);
    }

    @PostMapping(path = "/auth")
    public ResponseEntity authenticate(@RequestBody @Valid AuthDTO authDTO) {
        if (userService.isCardExist(authDTO.getCardNumber())
                && userService.getCreditCard(authDTO.getCardNumber(), authDTO.getPassword()) != null) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/send")
    public ResponseEntity sendMoney(@RequestBody @Valid SendRequestDTO sendRequestDTO ) {
        if (userService.isCardExist(sendRequestDTO.getSenderCardNumber())){
            if (userService.getCreditCard(sendRequestDTO.getSenderCardNumber(),
                    sendRequestDTO.getSenderPassword()) != null) {
                if (userService.sendMoney(sendRequestDTO.getSenderCardNumber(), sendRequestDTO.getReceiverCardNumber(),
                        sendRequestDTO.getAmount())) {
                    return new ResponseEntity(HttpStatus.OK);
                } else {
                    return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
                }
            }
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/cards")
    @ResponseStatus(HttpStatus.OK)
    public List<CreditCardDTO> getAllCards() {
        return userService.getAllCards();
    }

}

package com.example.restapiexample.service;

import com.example.restapiexample.dto.AuthDTO;
import com.example.restapiexample.dto.CreditCardDTO;
import com.example.restapiexample.model.User;
import com.example.restapiexample.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<CreditCardDTO> getAllCards() {
        return repository.findAllOrderByCreditCard().stream()
                .map(user -> convertToDTO(user))
                .collect(Collectors.toList());
    }

    public User save(CreditCardDTO creditCardDTO) {
        User user = convertToUser(creditCardDTO);
        return repository.save(user);
    }

    public boolean isCardExist(String creditCard) {
        return repository.existsUserByCreditCard(creditCard);
    }

    @Transactional
    public boolean sendMoney(String senderCardNumber, String receiverCardNumber, Float amount) {

    }

    public User getUser(String cardNumber, String password) {
        return repository.findUserByCreditCardAndPassword(cardNumber, password);
    }


    public CreditCardDTO convertToDTO(User user) {
        CreditCardDTO creditCardDTO = modelMapper.map(user, CreditCardDTO.class);
        return creditCardDTO;
    }

    public User convertToUser(CreditCardDTO creditCardDTO) {
        User user = modelMapper.map(creditCardDTO, User.class);
        return user;
    }

}

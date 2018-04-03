package com.example.restapiexample.service;

import com.example.restapiexample.dto.CreditCardDTO;
import com.example.restapiexample.model.CreditCard;
import com.example.restapiexample.model.User;
import com.example.restapiexample.repository.CreditCardRepository;
import com.example.restapiexample.repository.UserRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    public List<CreditCardDTO> getAllCards() {
        return creditCardRepository.findAllByOrderByNumberDesc().stream()
                .map(creditCard -> convertCreditCardToDTO(creditCard))
                .collect(Collectors.toList());
    }

    public User save(CreditCardDTO creditCardDTO) {
        User user = convertToUser(creditCardDTO);
        return userRepository.save(user);
    }

    public boolean isCardExist(String cardNumber) {
        return creditCardRepository.findById(cardNumber).isPresent();
    }

    @Transactional
    public boolean sendMoney(String senderCardNumber, String receiverCardNumber, Float amount) {
        CreditCard sender = creditCardRepository.findById(senderCardNumber).get();
        CreditCard receiver = creditCardRepository.findById(receiverCardNumber).get();
        if (sender.getBalance() >= amount) {
            sender.subtractMoney(amount);
            receiver.addMoney(amount);
            creditCardRepository.save(sender);
            creditCardRepository.save(receiver);
            return true;
        }
        return false;
    }

    public CreditCard getCreditCard(String cardNumber, String password) {
        return creditCardRepository.findCreditCardByNumberAndPassword(cardNumber, password);
    }

    private CreditCardDTO convertCreditCardToDTO(CreditCard creditCard) {
        CreditCardDTO creditCardDTO = new CreditCardDTO();
        creditCardDTO.setCardNumber(creditCard.getNumber());
        creditCardDTO.setUserAddress(creditCard.getUser().getAddress());
        creditCardDTO.setUserBirthday(creditCard.getUser().getBirthday());
        creditCardDTO.setUserGender(creditCard.getUser().getGender());
        creditCardDTO.setUserName(creditCard.getUser().getName());
        return creditCardDTO;
    }

    private User convertToUser(CreditCardDTO creditCardDTO) {
        User user = new User();
        user.setAddress(creditCardDTO.getUserAddress());
        user.setBirthday(creditCardDTO.getUserBirthday());
        user.setGender(creditCardDTO.getUserGender());
        user.setName(creditCardDTO.getUserName());
        CreditCard creditCard = new CreditCard();
        creditCard.setNumber(creditCardDTO.getCardNumber());
        creditCard.setPassword(RandomStringUtils.random(8, true, true));
        user.setCreditCard(creditCard);
        creditCard.setUser(user);
        return user;
    }

}

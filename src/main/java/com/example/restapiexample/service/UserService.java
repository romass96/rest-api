package com.example.restapiexample.service;

import com.example.restapiexample.dto.AuthDTO;
import com.example.restapiexample.dto.CreditCardDTO;
import com.example.restapiexample.model.CreditCard;
import com.example.restapiexample.model.User;
import com.example.restapiexample.repository.CreditCardRepository;
import com.example.restapiexample.repository.UserRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

//    public List<CreditCardDTO> getAllCards() {
//        return repository.findAllOrderByCreditCard().stream()
//                .map(user -> convertToDTO(user))
//                .collect(Collectors.toList());
//    }

    public User save(CreditCardDTO creditCardDTO) {
        User user = convertToUser(creditCardDTO);
        return userRepository.save(user);
    }

//    public boolean isCardExist(String creditCard) {
//        return repository.existsUserByCreditCard(creditCard);
//    }
//
//    @Transactional
//    public boolean sendMoney(String senderCardNumber, String receiverCardNumber, Float amount) {
//
//    }
//
//    public User getUser(String cardNumber, String password) {
//        return repository.findUserByCreditCardAndPassword(cardNumber, password);
//    }
//
//
//    public CreditCardDTO convertToDTO(User user) {
//        CreditCardDTO creditCardDTO = modelMapper.map(user, CreditCardDTO.class);
//        return creditCardDTO;
//    }
//
    public User convertToUser(CreditCardDTO creditCardDTO) {
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

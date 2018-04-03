package com.example.restapiexample.repository;

import com.example.restapiexample.model.CreditCard;
import com.example.restapiexample.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCard, String> {
    CreditCard findCreditCardByNumberAndPassword(String number, String password);
    List<CreditCard> findAllByOrderByNumberDesc();
}

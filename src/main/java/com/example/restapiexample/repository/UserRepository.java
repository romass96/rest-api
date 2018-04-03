package com.example.restapiexample.repository;

import com.example.restapiexample.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAllOrderByCreditCard();
    boolean existsUserByCreditCard(String creditCard);
    User findUserByCreditCardAndPassword(String creditCard, String password);
}

package com.southwind.springboottest.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Test
    void findAll(){
        System.out.println(userRepository.findAll());
    }

    @Test
    void deleteById(){
        userRepository.deleteById(12);
    }
}
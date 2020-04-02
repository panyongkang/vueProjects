package com.southwind.springboottest.repository;


import com.southwind.springboottest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository内部封装各种方法，使得BookRepository不用再写，直接调用即可
 */
public interface UserRepository extends JpaRepository<User,Integer> {


}

package com.southwind.springboottest.repository;


import com.southwind.springboottest.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository内部封装各种方法，使得BookRepository不用再写，直接调用即可
 */
public interface BookRepository extends JpaRepository<Book,Integer> {


}

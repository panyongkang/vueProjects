package com.southwind.springboottest.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

//    测试后端接口bookRepository是否查询得到数据库数据
    @Test
    void findAll(){
        System.out.println(bookRepository.findAll());
    }

}
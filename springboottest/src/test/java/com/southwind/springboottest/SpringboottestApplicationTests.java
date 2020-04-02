package com.southwind.springboottest;

import com.southwind.springboottest.entity.Book;
import com.southwind.springboottest.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
class SpringboottestApplicationTests {

    @Autowired
    private BookRepository bookRepository;

//    测试分页
    @Test
    void contextLoads() {
        PageRequest pageRequest=PageRequest.of(0,6);
        Page<Book> page=bookRepository.findAll(pageRequest);
        int i=0;
    }

//    测试添加方法是否能用
    @Test
    void save(){
        Book book = new Book();
        book.setName("围城");
        book.setAuthor("李四");
        Book book1=bookRepository.save(book);
        System.out.println(book1);
    }

    @Test
    void findById(){
        Book book=bookRepository.findById(1).get();
        System.out.println(book);
    }

    @Test
    void update(){
        Book book=new Book();
        book.setId(43);
        book.setName("李白");
        Book book1=bookRepository.save(book);
        System.out.println(book1);
    }

    @Test
    void delete(){
        bookRepository.deleteById(43);
    }
}

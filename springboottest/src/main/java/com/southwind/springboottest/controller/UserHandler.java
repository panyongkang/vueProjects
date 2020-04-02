package com.southwind.springboottest.controller;

import com.southwind.springboottest.entity.User;
import com.southwind.springboottest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/findAll/{page}/{size}")
    public Page<User> findAll(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        PageRequest request=PageRequest.of(page,size);
        return userRepository.findAll(request);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        userRepository.deleteById(id);
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") Integer id){
       return userRepository.findById(id).get();
    }

    @PutMapping("/update")
    public String update(@RequestBody User user){
        User result= userRepository.save(user);
        if (result!=null){
            return "success";
        }else{
            return "error";
        }
    }
}

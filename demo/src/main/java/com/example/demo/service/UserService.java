package com.example.demo.service;

import com.example.demo.exception.CustomException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void throwA(){
        throw new CustomException("Loi roi");
    }
}

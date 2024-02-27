package com.example.gdsc.service;

import com.example.gdsc.data.dto.SignInResultDto;
import com.example.gdsc.data.dto.SignUpResultDto;

public interface SignService {

  SignUpResultDto signUp(String id, String password, String name, String role);

  SignInResultDto signIn(String id, String password) throws RuntimeException;

}
package com.museum.service;

import com.museum.pojo.Admin;

import java.util.List;

public interface ALogInService {
    List<Admin> logInSearch(String username, String password);
}

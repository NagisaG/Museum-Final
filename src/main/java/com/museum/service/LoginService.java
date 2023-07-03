package com.museum.service;

import com.museum.pojo.Admin;

import java.util.List;

public interface LoginService {
    List<Admin> searchUser(String username, String password);
    List<Admin> logInSearch(String username,String password);
}

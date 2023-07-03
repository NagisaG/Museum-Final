package com.museum.mapper;

import com.museum.pojo.Admin;

import java.util.List;

public interface ALogInMapper {
    List<Admin> logInSearch(String username, String password);
}

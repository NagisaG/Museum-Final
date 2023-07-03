package com.museum.mapper;

import com.museum.pojo.Admin;

import java.util.List;

public interface LogMapper {
    List<Admin> searchUser(String username, String password);
    List<Admin> logInSearch(String username,String password);
}

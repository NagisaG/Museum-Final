package com.museum.service.impl;

import com.museum.mapper.ALogInMapper;
import com.museum.pojo.Admin;
import com.museum.service.ALogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 名称:ALogInServiceImpl
 * 描述:
 *
 * @version 1.0
 * @author:Nagisa
 * @datetime:2023-06-29 18:57
 */
@Primary
@Service
public class ALogInServiceImpl implements ALogInService {
    @Autowired(required = false)
    private ALogInMapper aLogInMapper;
    @Override
    public List<Admin> logInSearch(String username, String password) {
        return aLogInMapper.logInSearch(username, password);
    }
}

package com.museum.service.impl;

import com.museum.mapper.LogMapper;
import com.museum.pojo.Admin;
import com.museum.service.AdminService;
import com.museum.service.LoginService;
//import com.museum.vo.param.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.util.List;

/**
 * 名称:LoginServiceImpl
 * 描述:
 *
 * @version 1.0
 * @author:Nagisa
 * @datetime:2023-06-29 11:30
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired(required = false)
    private LogMapper logMapper;
    @Override
    public List<Admin> searchUser(String username, String password) {
        return logMapper.searchUser(username, password);
    }

    @Override
    public List<Admin> logInSearch(String username, String password) {
        return logMapper.logInSearch(username, password);
    }
}

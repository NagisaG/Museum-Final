package com.museum.service.impl;

import com.museum.mapper.AdminMapper;
import com.museum.pojo.Admin;
import com.museum.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 名称:AdminServiceImpl
 * 描述:AdminService接口的实现类
 *
 * @version 1.0
 * @author:Nagisa
 * @datetime:2023-06-26 16:52
 */
@Service
@Primary
public class AdminServiceImpl implements AdminService {

    @Autowired(required = false)
    private AdminMapper adminMapper;

    @Override
    public List<Admin> logIn(String username, String password) {
        return adminMapper.logIn(username, password);
    }

    @Override
    public List<Admin> findAllAdmins() {
        return adminMapper.findAllAdmins();
    }

    @Override
    public Admin getAdminWithPermissin(String username) {
        return adminMapper.getAdminWithPermissin(username);
    }

    @Override

    @ResponseBody
    public int insertAdmin(Admin admin) {
        return adminMapper.insertAdmin(admin);
    }

    @Override
    public List<Admin> findAdminByUsername(String username) {
        return adminMapper.findAdminByUsername(username);
    }

    @Override
    public List<Admin> findAdminByPermission(String username) {
        return adminMapper.findAdminByPermission(username);
    }

    @Override
    public int deleteAdminByName(String username) {
        return adminMapper.deleteAdminByName(username);
    }

    @Override
    public int updateAdmin(Admin admin) {
        return adminMapper.updateAdmin(admin);
    }

    @Override
    public int deleteAdmins(String[] usernames) {
        int count=0;
        for(String username:usernames){
            count+=adminMapper.deleteAdminByName(username);
        }
        return count;
    }
}

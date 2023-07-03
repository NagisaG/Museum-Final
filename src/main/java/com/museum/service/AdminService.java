package com.museum.service;

import com.museum.pojo.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> logIn(String username,String password);
    List<Admin> findAllAdmins();//查询全部管理员
    Admin getAdminWithPermissin(String username);//根据管理员返回权限
    int insertAdmin(Admin admin);//新增管理员
    List<Admin> findAdminByUsername(String username);//根据姓名查找
    List<Admin> findAdminByPermission(String username);//根据权限查询管理员
    int deleteAdminByName(String username);//删除管理员
    int updateAdmin(Admin admin);//修改管理员信息
    int deleteAdmins(String[] usernames);
}

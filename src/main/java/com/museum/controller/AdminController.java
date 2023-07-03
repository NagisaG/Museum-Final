package com.museum.controller;

import com.museum.pojo.Admin;
import com.museum.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 名称:AdminController
 * 描述:管理员的控制器
 *
 * @version 1.0
 * @author:Nagisa
 * @datetime:2023-06-26 16:45
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired(required = false)
    private AdminService adminService;
   /* @RequestMapping("/tologin")
    public String tologin(){
        return "adminlogin";
    }*/

    public Admin getAdminWithPermissin(@RequestParam(name="username") String username){
        Admin admin=adminService.getAdminWithPermissin(username);
        System.out.println(admin);
        return admin;
    }
    public boolean judgePermission(HttpServletRequest request){
        String username=(String) request.getSession().getAttribute("username");
        String permission=adminService.getAdminWithPermissin(username).getPermission();
        return permission.equals("一级");
    }
    @RequestMapping("/findall")
    public String findAllAdmins(Model model, HttpServletRequest request) {
        /*HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String permission=adminService.getAdminWithPermissin(username).getPermission();
        System.out.println("权限为："+permission);*/
        if (judgePermission(request)) {
            List<Admin> adminList = adminService.findAllAdmins();
            for (Admin admin : adminList)
                System.out.println(admin.getUsername());
            model.addAttribute("alist", adminList);
            return "AdminIndex";
        }
        else {
            model.addAttribute("msg","权限不足");
            return "public/false";
        }
    }
    @RequestMapping("/toinsert")
    public String toInsert(){
        return "Admin/InsertAdmin";
    }
    @RequestMapping("/insert")
    public String insertAdmin(Admin admin,Model model,HttpServletRequest request) {
        if(judgePermission(request)) {
            System.out.println("新增管理员信息:" + admin);
            int i = adminService.insertAdmin(admin);
            if (i > 0) {
                List<Admin> adminList = adminService.findAllAdmins();
                model.addAttribute("alist", adminList);
                model.addAttribute("msg", "添加成功");
                return "AdminIndex";
            } else {
                model.addAttribute("msg","新增管理员信息失败");
                return "public/false";
            }
        }else{
            model.addAttribute("msg","权限不足");
            return "public/false";
        }
    }

    @RequestMapping("/toupdate/{username}")
    public String toupdate(@PathVariable("username") String username,Model model,HttpServletRequest request){
        if(judgePermission(request)) {
            List<Admin> adminList = adminService.findAdminByUsername(username);
            Admin admin = adminList.get(0);
            model.addAttribute("admin",admin);
            return "Admin/UpdateAdmin";//返回的是admin文件夹下的updateadmin.html文件
        }
        else {
            model.addAttribute("msg","权限不足");
            return "public/false";
        }
    }

    @RequestMapping("/update")
    public String updateAdmin(Admin admin,Model model,HttpServletRequest request){
        if(judgePermission(request)){
            int i=adminService.updateAdmin(admin);
            if (i > 0) {
                // List<Museum> museumList1 = museumService.findAllMuseums();
                List<Admin> adminList1=adminService.findAllAdmins();
                model.addAttribute("alist", adminList1);
                model.addAttribute("msg", "更新成功");
                return "AdminIndex";//返回Admin文件夹下adminlist.html文件
            } else {
                model.addAttribute("msg","更新失败");
                return "public/false";
            }
        }
        else {
            model.addAttribute("msg","权限不足");
            return "public/false";
        }
    }

    @RequestMapping("/findbyname")
    public String findAdminByUsername(@RequestParam(value="searchStr") String searchStr,Model model,HttpServletRequest request){
        if (judgePermission(request)){
            List<Admin> adminList = adminService.findAdminByUsername(searchStr);
            if (adminList.size() > 0) {
                model.addAttribute("alist", adminList);
                model.addAttribute("msg", "查询成功");
                return "AdminIndex";
            } else {
                model.addAttribute("msg","查询失败");
                return "public/false";
            }
        }
        else {
            model.addAttribute("msg","权限不足");
            return "public/false";
        }
    }

    // List<Admin> findAdminByPermission(String username);//根据权限查询管理员
    @RequestMapping("/findbypermission")
    public String findAdminByPermission(@RequestParam(value = "permission") String permission,Model model,HttpServletRequest request){
        if (judgePermission(request)){
            List<Admin> adminList = adminService.findAdminByPermission(permission);
            if (adminList.size() > 0) {
                model.addAttribute("alist", adminList);
                model.addAttribute("msg", "查询成功");
                return "AdminIndex";
            } else {
                model.addAttribute("msg","查询失败");
                return "public/false";
            }
        }
        else {
            model.addAttribute("msg","权限不足");
            return "public/false";
        }
    }

    @RequestMapping("/deletebyname/{username}")
    public String deleteAdminByName(@PathVariable("username") String username,Model model,HttpServletRequest request){
        if(judgePermission(request)){
            int i=adminService.deleteAdminByName(username);
            if(i>0){
                List<Admin> adminList=adminService.findAllAdmins();
                model.addAttribute("alist",adminList);
                model.addAttribute("msg","删除管理员信息成功");
                return "AdminIndex";
            }
            else {
                model.addAttribute("msg","未找到指定用户");
                return "public/false";
            }
        } else {
            model.addAttribute("msg","权限不足");
            return "public/false";
        }
    }

    @RequestMapping("/delete")
    public String deleteAdmins(String[] usernames,Model model,HttpServletRequest request){
        if(judgePermission(request)){
            if(usernames!=null){
                int i=adminService.deleteAdmins(usernames);
                if(i>0){
                    List<Admin> adminList=adminService.findAllAdmins();
                    model.addAttribute("alist",adminList);
                    model.addAttribute("msg","删除管理员信息成功");
                    return "AdminIndex";
                }
                else {
                    model.addAttribute("msg","用户不存");
                    return "public/false";
                }
            }
            else {
                model.addAttribute("msg","未选择用户");
                return "public/false";
            }
        }
        else {
            model.addAttribute("msg","权限不足");
            return "public/false";
        }
    }
}

package com.museum.controller;

import com.museum.pojo.Museum;
import com.museum.service.MuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 名称:MuseumController
 * 描述:Museum的控制类
 *
 * @version 1.0
 * @author:Nagisa
 * @datetime:2023-06-25 17:26
 */
@Controller
@RequestMapping("/museum")
public class MuseumController {
    @Autowired(required = false)
    private MuseumService museumService;
    //跳转到系统首页
    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }

    @RequestMapping("/findall")
    public String findAllMuseums(Model model)
    {
        List<Museum> museumList=museumService.findAllMuseums();
        for(Museum museum:museumList)
            System.out.println(museum.getMuseum_name());
        model.addAttribute("mlist",museumList);
        return "MuseumIndex";
    }

    @RequestMapping("/findbyprovince")
    public String findMuseumByProvince(@RequestParam(value="province") String province, Model model)
    {
        // List<Museum> museumList=museumService.findAllMuseums();
        List<Museum> museumList=museumService.findMuseumByProvince(province);
        if(museumList.size()>0)
        {
            model.addAttribute("mlist",museumList);
            model.addAttribute("msg","查询成功");
            return "MuseumIndex";
        }
        else{
            return "public/false";
        }
    }
    @RequestMapping("/findbyname")
    public String findMuseumByName(@RequestParam(value="museum_name") String museum_name, Model model)
    {
        // List<Museum> museumList=museumService.findAllMuseums();
        List<Museum> museumList=museumService.findMuseumByName(museum_name);
        if(museumList.size()>0)
        {
            model.addAttribute("mlist",museumList);
            model.addAttribute("msg","查询成功");
            return "MuseumIndex";
        }
        else{
            return "public/false";
        }
    }

    @RequestMapping("/findbylevel")
    public String findMuseumByLevel(@RequestParam(value="level") String level, Model model) {
        // List<Museum> museumList=museumService.findAllMuseums();
        List<Museum> museumList = museumService.findMuseumByLevel(level);
        if (museumList.size() > 0) {
            model.addAttribute("mlist", museumList);
            model.addAttribute("msg", "查询成功");
            return "MuseumIndex";
        } else {
            return "public/false";
        }
    }

    @RequestMapping("/findbytype")
    public String findMuseumByType(@RequestParam(value="type") String type, Model model) {
        // List<Museum> museumList=museumService.findAllMuseums();
        List<Museum> museumList = museumService.findMuseumByType(type);
        if (museumList.size() > 0) {
            model.addAttribute("mlist", museumList);
            model.addAttribute("msg", "查询成功");
            return "MuseumIndex";
        } else {
            return "public/false";}
    }
    @RequestMapping("/findbyisfree")
    public String findMuseumByIsfree(@RequestParam(value="isfree") String isfree, Model model) {
        // List<Museum> museumList=museumService.findAllMuseums();
        List<Museum> museumList = museumService.findMuseumByIsfree(isfree);
        if (museumList.size() > 0) {
            model.addAttribute("mlist", museumList);
            model.addAttribute("msg", "查询成功");
            return "MuseumIndex";
        } else {
            return "public/false";}
    }

    @RequestMapping("/findbystr")
    public String findMuseumByStr(@RequestParam(value="searchStr") String searchStr, Model model) {
        // List<Museum> museumList=museumService.findAllMuseums();
        List<Museum> museumList = museumService.findMuseumByStr(searchStr);
        if (museumList.size() > 0) {
            model.addAttribute("mlist", museumList);
            model.addAttribute("msg", "查询成功");
            return "MuseumIndex";
        } else {
            return "public/false";}
    }

    @RequestMapping("/deletebyname/{museum_name}")
    public String deleteMuseumByName(@PathVariable("museum_name") String museum_name,Model model){
        int i=museumService.deleteMuseumByName(museum_name);
        if(i>0){
            List<Museum> museumList=museumService.findAllMuseums();
            model.addAttribute("mlist",museumList);
            model.addAttribute("msg","删除博物馆信息成功");
            return "MuseumIndex";
        }
        else {
            return "public/false";
        }
    }
    @RequestMapping("/toupdate/{museum_name}")
    public String toupdate(@PathVariable("museum_name")String museum_name,Model model){
        List<Museum> museumList=museumService.findMuseumByName(museum_name);
        model.addAttribute("museum",museumList.get(0));
        return "Museum/UpdateMuseum";
    }
    @RequestMapping("/update")
    public String updateMuseum(Museum museum,Model model){
        int i=museumService.updateMuseum(museum);
        if(i>0){
            List<Museum> museumList1=museumService.findAllMuseums();
            model.addAttribute("mlist",museumList1);
            model.addAttribute("msg","更新成功");
            return "MuseumIndex";
        }else{
            return "public/false";
        }
    }
    @RequestMapping("/toinsert")
    public String toInsert(){
        return "Museum/InsertMuseum";
    }
    @RequestMapping("/insert")
    public String insertMuseum(Museum museum,Model model){
        System.out.println("新增博物馆信息："+museum);
        int i=museumService.insertMuseum(museum);
        if(i>0){
            List<Museum> museumList=museumService.findAllMuseums();
            model.addAttribute("mlsist",museumList);
            model.addAttribute("msg","添加博物馆信息成功");
            return "MuseumIndex";
        }else{
            return "public/false";
        }
    }

}

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


@Controller
@RequestMapping("/guest/museum")
public class MuseumGuestController {
    @Autowired(required = false)
    private MuseumService museumService;

    //跳转到系统首页
    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }

    //跳转到添加博物馆页面
    @RequestMapping("/toinsert")
    public String toInsert(){
        return "museum/insertmuseum";
    }

    //添加博物馆信息
    @RequestMapping("/insert")
    public String insertBook(Museum museum, Model model){
        System.out.println("新增博物馆信息：" + museum);
        int i = museumService.insertMuseum(museum);
        if(i > 0){
            List<Museum> museumlist = museumService.findAllMuseums();
            model.addAttribute("mlist",museumlist);
            model.addAttribute("msg","添加博物馆信息成功");
            return "museum/museumlist";
        }
        else{
            return "public/false";
        }
    }

    //查询全部博物馆
    @RequestMapping("/findall")
    public String findAllMuseums(Model model){
        List<Museum> museumList=museumService.findAllMuseums();
        for(Museum museum:museumList)
            System.out.println(museum.getMuseum_name()+" "+museum.getProvince()+" "+museum.getLevel());
        model.addAttribute("mlist",museumList);
        return "museum/museumlist";
    }

    //模糊查询
    @RequestMapping("/findbystr")
    public String findMuseumByStr(@RequestParam(value = "searchStr") String searchStr, Model model){
        List<Museum> museumList=museumService.findMuseumByStr(searchStr);
        model.addAttribute("mlist",museumList);
        return "museum/museumlist";
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

    //跳转到博物馆页面
    @RequestMapping("/tomuseumlist")
    public String toMuseumlist(){
        return "museum/museumlist";
    }

    //分级查找
    @RequestMapping("/museumlist")
    public String findByLevel(String level,Model model){
        level="一级";
        List<Museum> museumlist=museumService.findByLevel(level);
        model.addAttribute("mlist",museumlist);
        return "museum/museumlist";
    }
    @RequestMapping("/museumlist2")
    public String findByLevel2(String level,Model model){
        level="二级";
        List<Museum> museumlist=museumService.findByLevel(level);
        model.addAttribute("mlist",museumlist);
        return "museum/museumlist";
    }
    @RequestMapping("/museumlist3")
    public String findByLevel3(String level,Model model){
        level="三级";
        List<Museum> museumlist=museumService.findByLevel(level);
        model.addAttribute("mlist",museumlist);
        return "museum/museumlist";
    }


    @RequestMapping("/toupdate/{museum_name}")
    public String toupdate(@PathVariable("museum_name")String museum_name,Model model){
        List<Museum> museumList=museumService.findMuseumByName(museum_name);
        model.addAttribute("museum",museumList.get(0));
        return "Museum/UpdateMuseum";
    }

    //修改博物馆信息
    @RequestMapping("/update")
    public String updateMuseum(Museum museum, Model model){
        int i = museumService.updateMuseum(museum);
        if(i > 0){
            List<Museum> museumlist = museumService.findAllMuseums();
            model.addAttribute("mlist",museumlist);
            model.addAttribute("msg","修改博物馆信息成功");
            return "museum/museumlist";
        }
        else{
            return "public/false";
        }
    }

    //根据名称删除
    @RequestMapping("/deletebyname/{museum_name}")
    public String deleteMuseumByName(@PathVariable("museum_name") String name, Model model){
        int i = museumService.deleteMuseumByName(name);
        if(i > 0){
            List<Museum> museumlist = museumService.findAllMuseums();
            model.addAttribute("mlist",museumlist);
            model.addAttribute("msg","删除博物馆信息成功");
            return "museum/museumlist";
        }
        else{
            return "public/false";
        }
    }

}

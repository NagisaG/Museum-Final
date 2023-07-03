package com.museum.controller;

import com.museum.pojo.Artifact;
import com.museum.pojo.News;
import com.museum.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/guest/news")
public class NewsGuestController {
    @Autowired(required = false)
    private NewsService newsService;

    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }

    @RequestMapping("/toinsert")
    public String toInsert(){
        return "news/insertnews";
    }


    @RequestMapping("/findbytitle")
    public News findNewsGuestByTitle(@RequestParam(name = "title") String title){
        News news = newsService.findNewsGuestByTitle(title);
        System.out.println(news);
        return news;
    }

    @RequestMapping("/showNews/{title}")
    public String showNews(@PathVariable("title") String title, Model model) {
        News news = newsService.findNewsGuestByTitle(title);
        model.addAttribute("news", news);
        return "artifact/news";
    }


    @RequestMapping("/findall")
    public String findAllNews(Model model){
        List<News> newsList=newsService.findAllNews();
        model.addAttribute("nlist",newsList);
        return "artifact/consult";
    }
}

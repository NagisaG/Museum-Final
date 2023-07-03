package com.museum.controller;

import com.museum.pojo.Artifact;
import com.museum.pojo.ExcelNews;
import com.museum.pojo.ExcelParser;
import com.museum.pojo.News;
import com.museum.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * 名称:NewsController
 * 描述:
 *
 * @version 1.0
 * @author:Nagisa
 * @datetime:2023-07-02 10:29
 */
@Controller
@RequestMapping("/news")
public class NewsController {
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

    @RequestMapping("/findall")
    public String findAllNews(Model model){
        List<News> newsList=newsService.findAllNews();
        for(News news:newsList)
            System.out.println(news.getTitle());
        model.addAttribute("nlist",newsList);
        return "NewsIndex";
    }

    @RequestMapping("/findbystr")
    public String findNewsByStr(@RequestParam(value="searchStr") String searchStr,Model model){
        List<News> newsList=newsService.findNewsByStr(searchStr);
        model.addAttribute("nlist",newsList);
        return "NewsIndex";
    }

    @RequestMapping("/findbytitle")
    public String findNewsByTitle(@RequestParam(value="title") String title,Model model){
        List<News> newsList=newsService.findNewsByTitle(title);
        if(newsList.size()>0) {
            model.addAttribute("nlist", newsList);
            return "NewsIndex";
        }else {
            model.addAttribute("msg","未找到指定文章");
            return "public/false";
        }
    }

    @RequestMapping("/deletebytitle/{title}")
    public String deleteNewsByTitle(@PathVariable("title") String title, Model model){
       int i=newsService.deleteNewsByTitle(title);
       if(i>0){
           List<News> newsList=newsService.findAllNews();
           model.addAttribute("nlist",newsList);
           model.addAttribute("msg","删除成功");
           return "NewsIndex";
       }else {
           model.addAttribute("msg","未找到指定文章，删除失败");
           return "public/false";
       }
    }

    @RequestMapping("/insert")
    public String insertNews(News news,Model model){
        System.out.println("新增新闻信息："+news);
        int i=newsService.insertNews(news);
        if(i>0){
            List<News> newsList=newsService.findAllNews();
            model.addAttribute("nlist",newsList);
            model.addAttribute("msg","添加新闻成功");
            return "NewsIndex";
        }
        else {
            model.addAttribute("msg","添加失败");
            return "public/false";
        }
    }

    @ResponseBody
    @RequestMapping("/upload")
    public String uploadArtifactExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        // 检查文件是否为空
        if (file.isEmpty()) {
            return "请选择要上传的文件";
        }
        try {
            // 示例：打印文件名和大小
            String fileName = file.getOriginalFilename();
            long fileSize = file.getSize();
            System.out.println("上传的文件名：" + fileName);
            System.out.println("文件大小：" + fileSize + " bytes");

            // 检查文件类型
            if (!fileName.toLowerCase().endsWith(".xlsx")) {
                return "请上传 *.xlsx 文件";
            }

            // 将MultipartFile对象转换为InputStream
            InputStream inputStream = file.getInputStream();

            // 创建ExcelNews对象并解析Excel文件
            ExcelNews parser = new ExcelNews();
            List<News> newsList = parser.parseExcelNews(inputStream);

            // 处理解析后的新闻对象列表
            for (News news : newsList) {
                List<News> existNewsList=newsService.findNewsByTitle(news.getTitle());
                //Artifact existArtifact = artifactAdminService.findArtifactByName(artifact.getArtname());
                //System.out.println(existNewsList);
                System.out.println("存在的新闻有："+existNewsList.size()+"条");
                if (existNewsList.size()>0) {
                    //artifact.setArtname(artifact.getArtname());
                    news.setTitle(news.getTitle());
                    System.out.println("error");
                    //artifactAdminService.updateArtifact(artifact);
                    newsService.updateNews(news);
                } else {
                    System.out.println("1");
                   // artifactAdminService.insertArtifact(artifact);
                  //  newsService.insertNews(news);
                    newsService.insertNews(news);
                }
                existNewsList.clear();
            }
            return "上传成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
    }

}

package com.museum.controller;

import com.museum.pojo.Artifact;
import com.museum.pojo.ExcelParser;

import com.museum.service.ArtifactAdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * 名称ArtifactController
 * 描述
 *
 * @version 1.0
 * @author:DuanMM
 * @datatime:2023-06-27 12:13
 */
@Controller
@RequestMapping("/artifact")
public class ArtifactAdminController {
    @Autowired(required = false)
    private ArtifactAdminService artifactAdminService;

    //跳转到系统首页
    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }

    //跳转到添加文物页面
    @RequestMapping("/toinsert")
    public String toInsert(){
        return "Artifact/InsertArtifact";
    }

    //添加文物信息
    @RequestMapping("/insert")
    public String insertArtifact(Artifact artifact, Model model){
        System.out.println("新增文物信息："+artifact);
        int i = artifactAdminService.insertArtifact(artifact);
        if(i > 0){
            List<Artifact> artifactlist = artifactAdminService.findAllArtifacts();
            model.addAttribute("alist",artifactlist);
            model.addAttribute("msg","添加文物信息成功");
            return "ArtifactIndex";
        }
        else{
            return "public/false";
        }
    }

    //查询全部文物
    @RequestMapping("/findall")
    public String findAllArtifacts(Model model){
        List<Artifact> artifactlist = artifactAdminService.findAllArtifacts();
        for(Artifact artifact:artifactlist)
            System.out.println(artifact.getArtname()+" " +artifact.getDynasty()+" "+artifact.getImage()+" "+artifact.getCollected());
        model.addAttribute("alist",artifactlist);
        return "ArtifactIndex";
    }

    //模糊查询
    @RequestMapping("/findbystr")
    public String findArtifactByStr(@RequestParam(value = "searchStr") String searchStr, Model model){
        List<Artifact> artifactlist= artifactAdminService.findArtifactByStr(searchStr);
        model.addAttribute("alist",artifactlist);
        return "ArtifactIndex";
    }

    //根据名称准确查找
    @RequestMapping("/findbyname")
    public Artifact findArtifactByName(@RequestParam(name="name") String name){
        Artifact artifact = artifactAdminService.findArtifactByName(name);
        System.out.println(artifact);
        return artifact;
    }

    //跳转到文物修改页面
    @RequestMapping("/toupdate/{artname}")
    public String toupdate(@PathVariable("artname") String name, Model model){
        Artifact artifact = artifactAdminService.findArtifactByName(name);
        model.addAttribute("artifact",artifact);
        return "Artifact/UpdateArtifact";
    }

    //修改文物信息
    @RequestMapping("/update")
    public String updateArtifact(Artifact artifact, Model model){
        int i = artifactAdminService.updateArtifact(artifact);
        if(i > 0){
            List<Artifact> artifactlist = artifactAdminService.findAllArtifacts();
            model.addAttribute("alist",artifactlist);
            model.addAttribute("msg","修改文物信息成功");
            return "ArtifactIndex";
        }
        else{
            return "public/false";
        }
    }

    //根据名称删除
    @RequestMapping("/deletebyname/{artname}")
    public String deleteArtifactByName(@PathVariable("artname") String name, Model model){
        int i = artifactAdminService.deleteArtifactByName(name);
        if(i > 0){
            List<Artifact> artifactlist = artifactAdminService.findAllArtifacts();
            model.addAttribute("alist",artifactlist);
            model.addAttribute("msg","删除文物信息成功");
            return "ArtifactIndex";
        }
        else{
            return "public/false";
        }
    }

    //批量删除
    @RequestMapping("/delete")
    public String deleteArtifacts(String[] names,Model model){
        if(names != null && names.length > 0){
            int i = artifactAdminService.deleteArtifacts(names);
            if(i > 0){
                List<Artifact> artifactlist = artifactAdminService.findAllArtifacts();
                model.addAttribute("alist",artifactlist);
                model.addAttribute("msg","批量删除文物成功");
                return "ArtifactIndex";
            }
            else{
                return "public/false";
            }
        }
        else{
            model.addAttribute("msg","未选择任何文物信息，无法删除");
            return "public/false";
        }
    }

    //上传
    @ResponseBody
    @RequestMapping("/upload")
    public String uploadArtifactExcel(@RequestParam("file") MultipartFile file) {
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

            // 创建ExcelParser对象并解析Excel文件
            ExcelParser parser = new ExcelParser();
            List<Artifact> artifacts = parser.parseExcel(inputStream);

            // 处理解析后的文物对象列表
            for (Artifact artifact : artifacts) {
                Artifact existArtifact = artifactAdminService.findArtifactByName(artifact.getArtname());
                if (existArtifact != null) {
                    artifact.setArtname(artifact.getArtname());
                    artifactAdminService.updateArtifact(artifact);
                } else {
                    artifactAdminService.insertArtifact(artifact);
                }
            }
            return "artifactindex";
        } catch (Exception e) {
            e.printStackTrace();
            return "public/false";
        }
    }
}


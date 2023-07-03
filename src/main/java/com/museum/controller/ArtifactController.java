package com.museum.controller;

import com.museum.pojo.Artifact;
import com.museum.pojo.ExcelParser;
import com.museum.service.ArtifactAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * 名称ArtifactController
 * 描述
 *
 * @version 1.0
 * @author:DuanMM
 * @datatime:2023-07-03 01:39
 */
@Controller
@RequestMapping("/guest/artifact")
public class ArtifactController {
    @Autowired(required = false)
    private ArtifactAdminService artifactService;

    //跳转到系统首页
    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }

    //跳转到添加文物页面
    @RequestMapping("/toinsert")
    public String toInsert(){
        return "artifact/insertartifact";
    }

    //添加文物信息
    @RequestMapping("/insert")
    public String insertArtifact(Artifact artifact, Model model){
        System.out.println("新增文物信息："+artifact);
        int i = artifactService.insertArtifact(artifact);
        if(i > 0){
            List<Artifact> artifactlist = artifactService.findAllArtifacts();
            model.addAttribute("alist",artifactlist);
            model.addAttribute("msg","添加文物信息成功");
            return "artifact/artifactlist";
        }
        else{
            return "public/false";
        }
    }

    //查询全部文物
    @RequestMapping("/findall")
    public String findAllArtifacts(Model model){
        List<Artifact> artifactlist = artifactService.findAllArtifacts();
        for(Artifact artifact:artifactlist)
            System.out.println(artifact.getArtname()+" " +artifact.getDynasty()+" "+artifact.getImage()+" "+artifact.getCollected());
        model.addAttribute("alist",artifactlist);
        return "artifact/artifactlist";
    }

    //模糊查询
    @RequestMapping("/findbystr")
    public String findArtifactByStr(@RequestParam(value = "searchStr1") String searchStr, Model model){
        List<Artifact> artifactlist=artifactService.findArtifactByStr(searchStr);
        model.addAttribute("alist",artifactlist);
        return "artifact/artifactlist";
    }

    //根据名称准确查找
    @RequestMapping("/findbyname")
    public Artifact findArtifactByName(@RequestParam(name = "name") String name){
        Artifact artifact = artifactService.findArtifactByName(name);
        System.out.println(artifact);
        return artifact;
    }

    @RequestMapping("/showIntroduction/{artname}")
    public String showIntroduction(@PathVariable("artname") String name, Model model) {
        Artifact artifact = artifactService.findArtifactByName(name);
        model.addAttribute("artifact", artifact);
        return "artifact/introduction";
    }

    //跳转到展览页面
    @RequestMapping("/toexhibition")
    public String toExhibition(){
        return "artifact/exhibition";
    }

    //跳转到资讯页面
    @RequestMapping("/toconsult")
    public String toConsult(){
        return "artifact/consult";
    }

    //跳转到新闻页面
    @RequestMapping("/tonew")
    public String toNew(){
        return "artifact/news";
    }

    //跳转到文物页面
    @RequestMapping("/toshow")
    public String toShow(){
        return "artifact/artifactshow";
    }

    //分类查找
    @RequestMapping("/artifactshow")//青铜器
    public String findByType(String type,Model model){
        type="青铜器";
        List<Artifact> artifactlist=artifactService.findByType(type);
        model.addAttribute("alist",artifactlist);
        return "artifact/artifactlist";
    }
    @RequestMapping("/artifactshow2")//漆木器
    public String findByType2(String type,Model model){
        type = "漆木器";
        List<Artifact> artifactlist=artifactService.findByType(type);
        model.addAttribute("alist",artifactlist);
        return "artifact/artifactlist";
    }
    @RequestMapping("/artifactshow3")//漆木器
    public String findByType3(String type,Model model){
        type = "金银器";
        List<Artifact> artifactlist=artifactService.findByType(type);
        model.addAttribute("alist",artifactlist);
        return "artifact/artifactlist";
    }
    @RequestMapping("/artifactshow4")//漆木器
    public String findByType4(String type,Model model){
        type = "竹简";
        List<Artifact> artifactlist=artifactService.findByType(type);
        model.addAttribute("alist",artifactlist);
        return "artifact/artifactlist";
    }
    @RequestMapping("/artifactshow5")//漆木器
    public String findByType5(String type,Model model){
        type = "玉器";
        List<Artifact> artifactlist=artifactService.findByType(type);
        model.addAttribute("alist",artifactlist);
        return "artifact/artifactlist";
    }
    @RequestMapping("/artifactshow6")//漆木器
    public String findByType6(String type,Model model){
        type = "陶瓷器";
        List<Artifact> artifactlist=artifactService.findByType(type);
        model.addAttribute("alist",artifactlist);
        return "artifact/artifactlist";
    }
    @RequestMapping("/artifactshow7")//漆木器
    public String findByType7(String type,Model model){
        type = "其它";
        List<Artifact> artifactlist=artifactService.findByType(type);
        model.addAttribute("alist",artifactlist);
        return "artifact/artifactlist";
    }

    //跳转到文物修改页面
    @RequestMapping("/toupdate/{artname}")
    public String toupdate(@PathVariable("artname") String name, Model model){
        Artifact artifact = artifactService.findArtifactByName(name);
        model.addAttribute("artifact",artifact);
        return "artifact/updateartifact";
    }

    //修改文物信息
    @RequestMapping("/update")
    public String updateArtifact(Artifact artifact, Model model){
        int i = artifactService.updateArtifact(artifact);
        if(i > 0){
            List<Artifact> artifactlist = artifactService.findAllArtifacts();
            model.addAttribute("alist",artifactlist);
            model.addAttribute("msg","修改文物信息成功");
            return "artifact/artifactlist";
        }
        else{
            return "public/false";
        }
    }

    //根据名称删除
    @RequestMapping("/deletebyname/{artname}")
    public String deleteArtifactByName(@PathVariable("artname") String name, Model model){
        int i = artifactService.deleteArtifactByName(name);
        if(i > 0){
            List<Artifact> artifactlist = artifactService.findAllArtifacts();
            model.addAttribute("alist",artifactlist);
            model.addAttribute("msg","删除文物信息成功");
            return "artifact/artifactlist";
        }
        else{
            return "public/false";
        }
    }

    //批量删除
    @RequestMapping("/delete")
    public String deleteArtifacts(String[] names,Model model){
        if(names != null && names.length > 0){
            int i = artifactService.deleteArtifacts(names);
            if(i > 0){
                List<Artifact> artifactlist = artifactService.findAllArtifacts();
                model.addAttribute("alist",artifactlist);
                model.addAttribute("msg","批量删除文物成功");
                return "artifact/artifactlist";
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
                Artifact existArtifact = artifactService.findArtifactByName(artifact.getArtname());
                if (existArtifact != null) {
                    artifact.setArtname(artifact.getArtname());
                    artifactService.updateArtifact(artifact);
                } else {
                    artifactService.insertArtifact(artifact);
                }
            }
            return "文件上传成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "文件上传失败";
        }
    }
}

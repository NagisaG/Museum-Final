package com.museum.controller;

import com.museum.service.ArtifactAdminService;
import com.museum.service.MuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 名称:EchartsController
 * 描述:
 *
 * @version 1.0
 * @author:Nagisa
 * @datetime:2023-07-02 23:42
 */
@Controller
public class EchartsController {

    @Autowired(required = false)
    private MuseumService museumService ;
    @Autowired(required = false)
    private ArtifactAdminService artifactAdminService;
    @RequestMapping("/index")

    public String doCharts(ModelMap modelMap){

        //根据省份查询，得到1为省份列表，2为对应数据
        List<String> province=new ArrayList<>();
        province.add("河北省");
        province.add("山西省");
        province.add("黑龙江省");
        province.add("吉林省");
        province.add("辽宁省");
        province.add("江苏省");
        province.add("浙江省");
        province.add("安徽省");
        province.add("福建省");
        province.add("江西省");
        province.add("山东省");
        province.add("河南省");
        province.add("湖北省");
        province.add("湖南省");
        province.add("广东省");
        province.add("海南省");
        province.add("四川省");
        province.add("贵州省");
        province.add("云南省");
        province.add("陕西省");
        province.add("甘肃省");
        province.add("青海省");
        province.add("台湾省");
        province.add("北京市");
        province.add("天津市");
        province.add("上海市");
        province.add("重庆市");
        province.add("香港特别行政区");
        province.add("澳门特别行政区");
        province.add("广西壮族自治区");
        province.add("西藏自治区");
        province.add("宁夏回族自治区");
        province.add("内蒙古自治区");
        province.add("新疆维吾尔自治区及新疆生产建设兵团 ");
        List<Integer> museumNumber=new ArrayList<>();
        for(String provinceName:province){
            int i=museumService.findMuseumByStr(provinceName).size();
            museumNumber.add(i);
            System.out.println(provinceName+":有"+i+"家");
        }

        String data1Json=JSON.toJSONString(province);
        String data2Json=JSON.toJSONString(museumNumber);

        modelMap.put("data1Json",data1Json);
        modelMap.put("data2Json",data2Json);


        //根据分类查询，数据3是类型列表，数据4是对应数量列表
        List<String> types=new ArrayList<>();
        types.add("青铜器");
        types.add("漆木器");
        types.add("金银器");
        types.add("竹简");
        types.add("玉器");
        types.add("陶瓷器");
        types.add("其它");
        List<Integer> numberOfType=new ArrayList<>();
        for(String type:types){
            numberOfType.add(artifactAdminService.findArtifactByStr(type).size());
        }
        String data3Json=JSON.toJSONString(types);
        String data4Json=JSON.toJSONString(numberOfType);

        modelMap.put("data3Json",data3Json);
        modelMap.put("data4Json",data4Json);

        //根据博物馆级别查询,5对应博物馆级别，6对应数据
        List<String> levels=new ArrayList<>();
        levels.add("一级");
        levels.add("二级");
        levels.add("三级");
        List<Integer> numberOfLevel=new ArrayList<>();
        for(String level:levels){
            numberOfLevel.add(museumService.findMuseumByLevel(level).size());
        }
        String data5Json=JSON.toJSONString(levels);
        String data6Json=JSON.toJSONString(numberOfLevel);

        modelMap.put("data5Json",data5Json);
        modelMap.put("data6Json",data6Json);


        //根据博物馆类别查询，7是类别，8是对应数据
        List<String> typesOfMuseum=new ArrayList<>();
        typesOfMuseum.add("文物系统国有博物馆");
        typesOfMuseum.add("其他行业国有博物馆");
        typesOfMuseum.add("非国有博物馆");
        List<Integer> numberOfMtypes=new ArrayList<>();
        for(String mtype:typesOfMuseum){
            numberOfMtypes.add(museumService.findMuseumByType(mtype).size());
        }
        String data7Json=JSON.toJSONString(typesOfMuseum);
        String data8Json=JSON.toJSONString(numberOfMtypes);

        modelMap.put("data7Json",data7Json);
        modelMap.put("data8Json",data8Json);

        return "AdminsIndex";

    }
}
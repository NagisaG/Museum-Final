package com.museum.mapper;

import com.museum.pojo.Museum;

import java.util.List;

public interface MuseumMapper {
    List<Museum> findAllMuseums();//查询全部图书馆
    List<Museum> findMuseumByProvince(String searchStr);//根据省分查询
    List<Museum> findMuseumByName(String searchStr);//根据博物馆名称查询
    List<Museum> findMuseumByLevel(String searchStr);//根据级别查询
    List<Museum> findMuseumByType(String searchStr);//根据类型查询
    List<Museum> findMuseumByIsfree(String searchStr);//根据是否免费查询
    List<Museum> findMuseumByStr(String searchStr);//模糊查询
    int insertMuseum(Museum museum);//新增博物馆
    int deleteMuseumByName(String museum_name);//删除博物馆
    int updateMuseum(Museum museum);//修改博物馆信息
    List<Museum> findByLevel(String level);//查找一级
    List<Museum> findByLevel2(String level);//查找二级
    List<Museum> findByLevel3(String level);//查找三级
}

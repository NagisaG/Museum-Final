package com.museum.service;

import com.museum.pojo.Museum;

import java.util.List;

public interface MuseumService {
    List<Museum> findAllMuseums();//查询全部图书馆
    List<Museum> findMuseumByProvince(String searchStr);
    List<Museum> findMuseumByName(String searchStr);
    List<Museum> findMuseumByLevel(String searchStr);
    List<Museum> findMuseumByType(String searchStr);
    List<Museum> findMuseumByIsfree(String searchStr);
    List<Museum> findMuseumByStr(String searchStr);
    int insertMuseum(Museum museum);
    int deleteMuseumByName(String museum_name);
    int updateMuseum(Museum museum);
    List<Museum> findByLevel(String level);//查找一级
    List<Museum> findByLevel2(String level);//查找二级
    List<Museum> findByLevel3(String level);//查找三级
}

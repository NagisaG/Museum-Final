package com.museum.service.impl;

import com.museum.mapper.MuseumMapper;
import com.museum.pojo.Museum;
import com.museum.service.MuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 名称:MuseumServiceImpl
 * 描述:MuseumService接口的实现类
 *
 * @version 1.0
 * @author:Nagisa
 * @datetime:2023-06-25 17:17
 */
@Service
@Primary
public class MuseumServiceImpl implements MuseumService {
    @Autowired(required = false)
    private MuseumMapper museumMapper;
    @Override
    public List<Museum> findAllMuseums() {
        return museumMapper.findAllMuseums();
    }

    @Override
    public List<Museum> findMuseumByProvince(String searchStr) {
        return museumMapper.findMuseumByProvince(searchStr);
    }

    @Override
    public List<Museum> findMuseumByName(String searchStr) {
        return museumMapper.findMuseumByName(searchStr);
    }

    @Override
    public List<Museum> findMuseumByLevel(String searchStr) {
        return museumMapper.findMuseumByLevel(searchStr);
    }

    @Override
    public List<Museum> findMuseumByType(String searchStr) {
        return museumMapper.findMuseumByType(searchStr);
    }

    @Override
    public List<Museum> findMuseumByIsfree(String searchStr) {
        return museumMapper.findMuseumByIsfree(searchStr);
    }

    @Override
    public List<Museum> findMuseumByStr(String searchStr) {
        return museumMapper.findMuseumByStr(searchStr);
    }

    @Override
    public int insertMuseum(Museum museum) {
        return museumMapper.insertMuseum(museum);
    }

    @Override
    public int deleteMuseumByName(String museum_name) {
        return museumMapper.deleteMuseumByName(museum_name);
    }

    @Override
    public int updateMuseum(Museum museum) {
        return museumMapper.updateMuseum(museum);
    }


    @Override
    public List<Museum> findByLevel(String level) {
        return museumMapper.findByLevel(level);
    }
    @Override
    public List<Museum> findByLevel2(String level) {
        return museumMapper.findByLevel2(level);
    }
    @Override
    public List<Museum> findByLevel3(String level) {
        return museumMapper.findByLevel3(level);
    }
}

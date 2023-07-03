package com.museum.service.impl;

import com.museum.mapper.NewsMapper;
import com.museum.pojo.News;
import com.museum.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 名称:NewsServiceImpl
 * 描述:
 *
 * @version 1.0
 * @author:Nagisa
 * @datetime:2023-07-02 10:30
 */
@Service
@Primary
public class NewsServiceImpl implements NewsService {
    @Autowired(required = false)
    private NewsMapper newsMapper;
    @Override
    public List<News> findAllNews() {
        return newsMapper.findAllNews();
    }

    @Override
    public List<News> findNewsByTitle(String title) {
        return newsMapper.findNewsByTitle(title);
    }

    @Override
    public int deleteNewsByTitle(String title) {
        return newsMapper.deleteNewsByTitle(title);
    }

    @Override
    public List<News> findNewsByStr(String searchStr) {
        return newsMapper.findNewsByStr(searchStr);
    }

    @Override
    public int updateNews(News news) {
        return newsMapper.updateNews(news);
    }

    @Override
    public int insertNews(News news) {
        return newsMapper.insertNews(news);
    }

    @Override
    public News findNewsGuestByTitle(String title) {
        return newsMapper.findNewsGuestByTitle(title);
    }
}

package com.museum.mapper;

import com.museum.pojo.News;

import java.util.List;

public interface NewsMapper {
    List<News> findAllNews();
    List<News> findNewsByTitle(String title);
    int deleteNewsByTitle(String title);
    List<News> findNewsByStr(String searchStr);
    int updateNews(News news);
    int insertNews(News news);
    News findNewsGuestByTitle(String title);//根据名称准确查询
}

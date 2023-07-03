package com.museum.service;

import com.museum.pojo.Artifact;

import java.util.List;

public interface ArtifactService {
    int insertArtifact(Artifact artifact);//添加文物
    List<Artifact> findAllArtifacts();//查找全部文物
    List<Artifact> findArtifactByStr(String searchStr);//模糊查询文物
    int updateArtifact(Artifact artifact);//修改文物信息
    Artifact findArtifactByName(String name);//根据名称准确查询
    int deleteArtifactByName(String name);//根据名称删除
    int deleteArtifacts(String[] names);//批量删除
    List<Artifact> findByType(String type);//查找青铜器类
    List<Artifact> findByType2(String type);//查找漆木器类
    List<Artifact> findByType3(String type);//查找金银器类
    List<Artifact> findByType4(String type);//查找竹筒类
    List<Artifact> findByType5(String type);//查找玉器类
    List<Artifact> findByType6(String type);//查找陶瓷器类
    List<Artifact> findByType7(String type);//查找其它类
}

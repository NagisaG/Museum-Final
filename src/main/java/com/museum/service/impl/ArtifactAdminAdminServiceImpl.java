package com.museum.service.impl;

import com.museum.mapper.ArtifactMapper;
import com.museum.pojo.Artifact;
import com.museum.service.ArtifactAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 名称ArtifactServiceImpl
 * 描述
 *
 * @version 1.0
 * @author:DuanMM
 * @datatime:2023-06-27 12:14
 */
@Service
@Primary
public class ArtifactAdminAdminServiceImpl implements ArtifactAdminService {
    @Autowired(required = false)
    private ArtifactMapper artifactMapper;

    @Override
    public int insertArtifact(Artifact artifact) {
        return artifactMapper.insertArtifact(artifact);
    }

    @Override
    public List<Artifact> findAllArtifacts() {
        return artifactMapper.findAllArtifacts();
    }

    @Override
    public List<Artifact> findArtifactByStr(String searchStr) {
        return artifactMapper.findArtifactByStr(searchStr);
    }

    @Override
    public int updateArtifact(Artifact artifact) {
        return artifactMapper.updateArtifact(artifact);
    }

    @Override
    public Artifact findArtifactByName(String name) {
        return artifactMapper.findArtifactByName(name);
    }

    @Override
    public int deleteArtifactByName(String name) {
        return artifactMapper.deleteArtifactByName(name);
    }

    @Override
    public int deleteArtifacts(String[] names) {
        int count = 0;
        for(String name:names){
            count += artifactMapper.deleteArtifactByName(name);//逐个删除
        }
        return count;//返回删除总数
    }

    @Override
    public List<Artifact> findByType(String type) {
        return artifactMapper.findByType(type);
    }
    @Override
    public List<Artifact> findByType2(String type) {
        return artifactMapper.findByType2(type);
    }
    @Override
    public List<Artifact> findByType3(String type) {
        return artifactMapper.findByType3(type);
    }
    @Override
    public List<Artifact> findByType4(String type) {
        return artifactMapper.findByType4(type);
    }
    @Override
    public List<Artifact> findByType5(String type) {
        return artifactMapper.findByType5(type);
    }
    @Override
    public List<Artifact> findByType6(String type) {
        return artifactMapper.findByType6(type);
    }
    @Override
    public List<Artifact> findByType7(String type) {
        return artifactMapper.findByType7(type);
    }


}

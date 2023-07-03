package com.museum.pojo;

import lombok.Data;

/**
 * 名称Artifact
 * 描述文物类
 *
 * @version 1.0
 * @author:DuanMM
 * @datatime:2023-06-27 12:09
 */
@Data
public class Artifact {
    private String artname;//名称
    private String dynasty;//时代
    private String type;//类型
    private String place;//出土地点
    private String time;//出土时间
    private String collected;//现藏地点
    private String image;//图片
    private String introduction;//介绍


    public Artifact(String artname, String dynasty, String type, String place, String time, String collected, String image, String introduction) {
        this.artname = artname;
        this.dynasty = dynasty;
        this.type = type;
        this.place = place;
        this.time = time;
        this.collected = collected;
        this.image = image;
        this.introduction = introduction;
    }

    public Artifact() {

    }
}

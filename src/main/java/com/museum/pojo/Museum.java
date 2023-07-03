package com.museum.pojo;

import lombok.Data;

/**
 * 名称:Museum
 * 描述:Museum类，包含博物馆的信息，与数据库信息对应
 *
 * @version 1.0
 * @author:Nagisa
 * @datetime:2023-06-25 17:28
 */
@Data
public class Museum {
    private String province;//博物馆所在省份
    private String museum_name;//博物馆名称
    private String level;//博物馆级别
    private String type;//博物馆类型
    private String isfree;//博物馆是否免费
}

/**
 * Copyright (C), 2019-2022, 海安秩明网络科技工作室
 * FileName: Student
 * Author:   HYJ
 * Date:     2019/3/11 1:45
 * Description:
 * History:
 * <author>          <微信号>          <version>          <工作室官网>
 * mirror_huang     wwwmirror95top      1.0.0            http://www.mirror95.top
 * 海安秩明网络科技工作室致力于企业网站设计、制作、源码出售、SEO推广，爬虫，微信公众号、小程序开发。
 * 希望为中小微型企业、个体户拓宽销售渠道，紧追互联网+潮流，实现合作共赢。
 * 秩明网络愿与您合作，共创美好生活。
 */
package com.mirror95.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @author: mirror_huang
 * @date: 2019/3/11 01:45
 * @qq: 1755496180
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable{

    private static final long serialVersionUID = 6933099205338248547L;
    /**
     * id
     */
    @Id
    private String id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;


}

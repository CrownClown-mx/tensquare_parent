package com.tensquare.recruit.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: Feng.Wang
 * @Company: Zelin.ShenZhen
 * @Description:
 * @Date: Create in 2019/3/23 15:37
 */
@Entity
@Table(name = "tb_enterprise")
@Data
public class Enterprise {
    @Id
    private String id;
    private String name;
    private String summary;
    private String address;
    private String labels;
    private String coordinate;
    private String ishot;
    private String logo;
    private String jobcount;
    private String url;

    public Enterprise() {
    }

    public Enterprise(String name, String summary, String address, String labels, String coordinate, String ishot, String logo, String jobcount, String url) {
        this.name = name;
        this.summary = summary;
        this.address = address;
        this.labels = labels;
        this.coordinate = coordinate;
        this.ishot = ishot;
        this.logo = logo;
        this.jobcount = jobcount;
        this.url = url;
    }

    public Enterprise(String id, String name, String summary, String address, String labels, String coordinate, String ishot, String logo, String jobcount, String url) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.address = address;
        this.labels = labels;
        this.coordinate = coordinate;
        this.ishot = ishot;
        this.logo = logo;
        this.jobcount = jobcount;
        this.url = url;
    }


}

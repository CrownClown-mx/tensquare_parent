package com.tensquare.user.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_admin")
@Data
public class Admin {
	@Id
	private String id;//ID
	private String loginname;//登陆名称
	private String password;//密码
	private String state;//状态
}

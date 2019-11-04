package com.tensquare.article.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_channel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Channel {

	@Id
	private String id;//ID
	private String name;//频道名称
	private String state;//状态
}

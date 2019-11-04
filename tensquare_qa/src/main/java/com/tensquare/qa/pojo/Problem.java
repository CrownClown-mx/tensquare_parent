package com.tensquare.qa.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tb_problem")
@Data
public class Problem {
  @Id
  private String id;
  private String title;
  private String content;
  private java.sql.Timestamp createtime;
  private java.sql.Timestamp updatetime;
  private String userid;
  private String nickname;
  private long visits;
  private long thumbup;
  private long reply;
  private String solve;
  private String replyname;
  private java.sql.Timestamp replytime;



}

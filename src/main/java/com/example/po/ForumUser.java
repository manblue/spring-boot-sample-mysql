package com.example.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tb_forum_user")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })//json 转换问题
public class ForumUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8749250934907008329L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
	@Column(name = "user_id")
    private String userId;  
	@Column(name = "user_name")
    private String userName;
	@Column(name = "nick_name")
    private String nickName;
	@Column(name = "website")
    private String website;
    
	public ForumUser(){}
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
    
    
    
}

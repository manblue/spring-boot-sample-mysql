package com.example.service.impl;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.data.ForumUserRepository;
import com.example.po.ForumUser;
import com.example.service.inter.ForumUserService;

@Service("forumUserService")
public class ForumUserServiceImpl implements ForumUserService {

	@Autowired
	ForumUserRepository forumUserRep;
	
	@Override
	public ForumUser add(String userId,String userName,String nickName,String website) {
		ForumUser user = new ForumUser();
		user.setUserId(userId);
		user.setNickName(nickName);
		user.setUserName(userName);
		user.setWebsite(website);
		return forumUserRep.save(user);
	}
	
	@Override
	public ForumUser add(ForumUser user) {
		user.setId(null);
		System.out.println("user.getNickName():"+new String(user.getNickName().getBytes(), Charset.forName("UTF-8"))+"ttttttttttt");
		return forumUserRep.save(user);
	}
	
	@Override
	public List<ForumUser> withUserIdAndWebSiteQuery(String userId, String website) {
		return forumUserRep.withUserIdAndWebSiteQuery(userId, website);
	}
	
	@Override
	public ForumUser findById(Long id) {
		return forumUserRep.getOne(id);
	}
}

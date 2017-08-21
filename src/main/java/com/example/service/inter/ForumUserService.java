package com.example.service.inter;

import java.util.List;

import com.example.po.ForumUser;

public interface ForumUserService {

	ForumUser add(String userId, String userName, String nickName,
			String website);

	ForumUser add(ForumUser user);

	List<ForumUser> withUserIdAndWebSiteQuery(String userId, String website);

	ForumUser findById(Long id);

}

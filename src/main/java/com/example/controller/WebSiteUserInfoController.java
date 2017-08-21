package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.LYCReponse;
import com.example.po.ForumUser;
import com.example.service.inter.ForumUserService;

@RestController
@RequestMapping( "/webSiteUserInfo")
public class WebSiteUserInfoController {

	private static Logger logger = LoggerFactory.getLogger(WebSiteUserInfoController.class);
	
	@Autowired
	ForumUserService forumUserService;
	
	@RequestMapping("/add")
	public LYCReponse add(String userId,String userName,String nickName,String website) {
		ForumUser user = forumUserService.add(userId, userName, nickName, website);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("user", user);
		return LYCReponse.addSuccessResponse("ok", data);
	}
	
	@RequestMapping("/addBody")
	public LYCReponse addBody(@RequestBody ForumUser user) {
		user = forumUserService.add(user);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("user", user);
		return LYCReponse.addSuccessResponse("ok", data);
	}
	
	@RequestMapping("/find1")
	public LYCReponse find1(String userId, String website) {
		List<ForumUser> users = forumUserService.withUserIdAndWebSiteQuery(userId, website);
		return LYCReponse.addListResponse("ok", users);
	}
	
	@RequestMapping("/findById")
	public LYCReponse findById(@RequestParam(name="id")String id) {
		ForumUser user =  forumUserService.findById(Long.valueOf(id));
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("user", user);
		return LYCReponse.addSuccessResponse("ok", data);
	}
}

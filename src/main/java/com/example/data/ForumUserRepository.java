package com.example.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.po.ForumUser;

public interface ForumUserRepository extends JpaRepository<ForumUser, Long> {

    @Query("select p from ForumUser p where p.userId= :userId and p.website= :website")  
    List<ForumUser> withUserIdAndWebSiteQuery(@Param("userId")String userId,@Param("website")String website);  
}

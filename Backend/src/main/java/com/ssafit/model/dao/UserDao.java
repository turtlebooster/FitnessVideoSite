package com.ssafit.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafit.model.dto.User;

public interface UserDao {
	// 유저 추가
	void insertUser(User user) throws Exception;
	// 유저 찾기
	User selectById(String id) throws Exception;
	// 유저 정보 수정
	void updateUser(User user);
	// 유저 탈퇴?
	void deleteUser(String id);
	
	// 팔로우 리스트
	List<User> selectFollowListById(String id);
	// 팔로워 리스트
	List<User> selectFollowerListById(String id);	
	// 팔로우 추가
	void insertFollow(@Param("userId") String userId,@Param("followId") String followId);
	// 팔로우 삭제
	void deleteFollow(@Param("userId") String userId,@Param("followId") String followId);
	
	// like video 추가
	void insertLikeVideo(@Param("userId") String userId,@Param("videoId") String videoId);
	// like video 삭제
	void deleteLikeVideo(@Param("userId") String userId,@Param("videoId") String videoId);
}
// https://dblee.tistory.com/145
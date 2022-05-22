package com.ssafit.model.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafit.model.dto.User;

public interface UserService {
	// 회원가입
	void join(User user) throws Exception;
	// 로그인
	User login(String id, String password) throws Exception;
	// 회원 정보 수정
	void modifyUser(User user) throws Exception;
	// 회원 탈퇴
	void removeUser(String id);
	// 회원 정보 받아오기
	User getUserById(String id) throws Exception;
	
	
	// 팔로우 리스트
	List<User> getFollowListById(String id);
	// 팔로워 리스트
	List<User> getFollowerListById(String id);	
	// 팔로우 추가
	void writeFollow(@Param("userId") String userId,@Param("followId") String followId);
	// 팔로우 삭제
	void removeFollow(@Param("userId") String userId,@Param("followId") String followId);	
	
	// like video 추가
	void writeLikeVideo(String userId, String videoId);
	// like video 삭제
	void removeLikeVideo(String userId, String videoId);
}

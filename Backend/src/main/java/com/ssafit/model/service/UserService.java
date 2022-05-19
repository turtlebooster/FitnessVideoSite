package com.ssafit.model.service;

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
	
	// like video 추가
	void writeLikeVideo(String userId, String videoId);
	// like video 삭제
	void removeLikeVideo(String userId, String videoId);
}

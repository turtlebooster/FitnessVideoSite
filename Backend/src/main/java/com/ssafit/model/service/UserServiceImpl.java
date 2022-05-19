package com.ssafit.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.exception.PWIncorrectException;
import com.ssafit.exception.UserNotFoundException;
import com.ssafit.model.dao.UserDao;
import com.ssafit.model.dto.User;
import com.ssafit.util.SHA256;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Override
	public void join(User user) throws Exception {
		// 비밀번호 암호화
		user.setPassword(new SHA256().getHash(user.getPassword()));
		userDao.insertUser(user);		
	}

	@Override
	public User login(String id, String password) throws Exception {
		User user = userDao.selectById(id);
		if(user == null) { // id 가 목록에 없는 경우
			throw new UserNotFoundException();
		}
		// 있는 경우
		// 비밀번호가 일치하는지 확인
		if(!user.getPassword().equals(new SHA256().getHash(password))) {
			throw new PWIncorrectException();
		} else return user;
	}

	@Override
	public void modifyUser(User user) throws Exception {
		System.out.println(user.getId());
		User origin = userDao.selectById(user.getId());
		if(origin == null) { // id 가 목록에 없는 경우
			throw new UserNotFoundException();
		}
		System.out.println(origin);
		origin.setEmail(user.getEmail());
		origin.setNickname(user.getNickname());
		origin.setPassword(new SHA256().getHash(user.getPassword()));
		userDao.updateUser(origin);
	}

	@Override
	public void removeUser(String id) {
		userDao.deleteUser(id);
	}

	@Override
	public void writeLikeVideo(String userId, String videoId) {
		userDao.insertLikeVideo(userId, videoId);
	}

	@Override
	public void removeLikeVideo(String userId, String videoId) {
		userDao.deleteLikeVideo(userId, videoId);
	}

}

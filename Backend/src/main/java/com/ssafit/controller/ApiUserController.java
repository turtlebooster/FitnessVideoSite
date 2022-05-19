package com.ssafit.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.model.dto.User;
import com.ssafit.model.service.UserService;
import com.ssafit.util.JWTUtil;

@RestController
@RequestMapping("/user")
public class ApiUserController{
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@PostMapping("/join")
	public void join(User user){
		 try {
			userService.join(user);
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(String id, String password){
		User user = null;
		HttpStatus status = null;
		HashMap<String, Object> result = new HashMap<>();
		try {
			user = userService.login(id, password);
			if(user.getId() != null || user.getId().length() > 0) {
				result.put("auth-token", jwtUtil.createToken("userId", user.getId()));
				
				result.put("message", SUCCESS);				
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(result , status);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody User user){
		System.out.println(user.getId());
		try {
			userService.modifyUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable String id){
		userService.removeUser(id);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);		
	}
	
	@PostMapping("/write")
	public ResponseEntity<String> writelikevideo(String userId, String videoId){
		userService.writeLikeVideo(userId, videoId);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deletelikevideo/{userId}/{videoId}")
	public ResponseEntity<String> deletelikevideo(@PathVariable String userId, @PathVariable String videoId){
		userService.removeLikeVideo(userId, videoId);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);		
	}
	
	
}

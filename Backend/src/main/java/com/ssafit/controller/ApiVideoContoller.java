package com.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.model.dto.Video;
import com.ssafit.model.service.VideoService;

@RestController
@RequestMapping("/video")
public class ApiVideoContoller {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private VideoService videoService;
	
	@GetMapping("list")
	public ResponseEntity<List<Video>> list(){
		return new ResponseEntity<List<Video>>(videoService.getVideoList(), HttpStatus.OK);
	}
	@GetMapping("detail/{videoId}")
	public ResponseEntity<Video> detail(@PathVariable String videoId){
		return new ResponseEntity<Video>(videoService.watchVideo(videoId), HttpStatus.OK);
	}
	@GetMapping("like/{userId}")
	public ResponseEntity<List<Video>> likeList(@PathVariable String userId){		
		return new ResponseEntity<List<Video>>(videoService.getVideoLikeList(userId), HttpStatus.OK);
	}
	
	
}

package com.ssafit.controller;

import java.util.List;

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

import com.ssafit.model.dto.Review;
import com.ssafit.model.service.ReviewService;

@RestController
@RequestMapping("/review")
public class ApiReviewController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/listByVideoId/{videoId}/{param}")
	public ResponseEntity<List<Review>> listByVideoId(@PathVariable String videoId, @PathVariable int param){		
		return new ResponseEntity<List<Review>>(reviewService.getReviewListByVideoId(videoId, param),  HttpStatus.OK);
	}
	@GetMapping("/listByUserId/{userId}")
	public ResponseEntity<List<Review>> listByUserId(@PathVariable String userId){		
		return new ResponseEntity<List<Review>>(reviewService.getReviewListByUserId(userId),  HttpStatus.OK);
	}
	@PostMapping("/write")
	public ResponseEntity<String> write(Review review){
		reviewService.writeReview(review); 
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody Review review){
		reviewService.modifyReview(review);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}	
	@DeleteMapping("/delete/{no}")
	public ResponseEntity<String> delete(@PathVariable int no){
		reviewService.removeReview(no);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);		
	}
	
	
}

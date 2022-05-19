package com.ssafit.model.service;

import java.util.List;

import com.ssafit.model.dto.Review;

public interface ReviewService {
	// videoId 입력으로 리뷰 목록 가져오기
	List<Review> getReviewListByVideoId(String videoId, int param);
	// userId 입력으로 리뷰 목록 가져오기
	List<Review> getReviewListByUserId(String userId);
	// 리뷰 하나 가져오기 
	Review getReviewOne(int no);
	// 리뷰 작성
	void writeReview(Review review);
	// 리뷰 수정
	void modifyReview(Review review);
	// 리뷰 삭제
	void removeReview(int no);
}

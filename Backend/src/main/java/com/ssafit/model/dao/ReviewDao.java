package com.ssafit.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafit.model.dto.Review;

public interface ReviewDao {
	// videoId 입력으로 리뷰 목록 가져오기 - param 값으로 정렬 방법 정의(default 0: 좋아요 순, 1: 최신 순 2: 오래된 순)
	List<Review> selectReviewListByVideoId(@Param("videoId") String videoId,@Param("param") int param);
	// userId 입력으로 리뷰 목록 가져오기
	List<Review> selectReviewListByUserId(String userId);
	// 리뷰 하나 가져오기 
	Review selectReviewOne(int no);
	// 리뷰 작성
	void insertReview(Review review);
	// 리뷰 수정
	void updateReview(Review review);
	// 리뷰 삭제
	void deleteReview(int no);
}

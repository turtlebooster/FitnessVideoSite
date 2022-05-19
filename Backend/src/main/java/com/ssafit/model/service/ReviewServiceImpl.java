package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.ReviewDao;
import com.ssafit.model.dto.Review;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	private ReviewDao reviewDao;
	
	@Override
	public List<Review> getReviewListByVideoId(String videoId, int param) {		
		return reviewDao.selectReviewListByVideoId(videoId, param);
	}

	@Override
	public List<Review> getReviewListByUserId(String userId) {
		return reviewDao.selectReviewListByUserId(userId);
	}

	@Override
	public Review getReviewOne(int no) {
		return reviewDao.selectReviewOne(no);
	}

	@Override
	public void writeReview(Review review) {
		reviewDao.insertReview(review);
	}

	@Override
	public void modifyReview(Review review) {
		reviewDao.updateReview(review);
	}

	@Override
	public void removeReview(int no) {
		reviewDao.deleteReview(no);
	}

}

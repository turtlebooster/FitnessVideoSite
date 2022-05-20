package com.ssafit.model.service;

import java.util.List;

import com.ssafit.model.dto.Video;

public interface VideoService {
	// 비디오 목록 가져오기
	List<Video> getVideoList(String part);
	// 비디오 하나 골라 가져오기
	Video getVideoOne(String videoId);
	// 비디오 보기
	Video watchVideo(String videoId);
	// 유저별 비디오 리스트 가져오기
	List<Video> getVideoLikeList(String userId);
	// 검색해서 비디오 목록 가져오기
	List<Video> getVideoListBySearch(String keyword);
}

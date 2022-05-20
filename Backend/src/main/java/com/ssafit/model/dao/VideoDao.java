package com.ssafit.model.dao;

import java.util.List;

import com.ssafit.model.dto.Video;

public interface VideoDao {
	// 전체 목록 불러오는 기능
	List<Video> selectVideoList();
	// 하나의 비디오만 골라서 가져오는 기능
	Video selectVideoById(String videoId);
	// 조회수 변경
	void updateViewCnt(Video video);
	// 유저가 좋아한 비디오 목록 가져오는 기능
	List<Video> selectVideoLikeList(String userId);
	// 부위 별 목록 가져오기
	List<Video> selectVideoListByPart(String part);
	// 키워드 검색 으로 비디오 목록 가져오기
	List<Video> selectVideoListBySearch(String keyword);
}

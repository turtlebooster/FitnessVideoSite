package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.VideoDao;
import com.ssafit.model.dto.Video;

@Service
public class VideoServiceImpl implements VideoService {
	@Autowired
	private VideoDao videoDao;
	
	@Override
	public List<Video> getVideoList() {		
		return videoDao.selectVideoList();
	}

	@Override
	public Video getVideoOne(String videoId) {
		return videoDao.selectVideoById(videoId);
	}

	@Override
	public Video watchVideo(String videoId) {
		Video video = this.getVideoOne(videoId);
		video.setViewCnt(video.getViewCnt() + 1);
		videoDao.updateViewCnt(video);
		return video;
	}

	@Override
	public List<Video> getVideoLikeList(String userId) {		
		return videoDao.selectVideoLikeList(userId);
	}

}

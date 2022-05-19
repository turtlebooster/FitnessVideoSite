package com.ssafit.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafit.model.dto.Calander;

public interface CalanderDao {
	// 유저 id로 캘린더 등록 목록 불러오기
	List<Calander> selectCalanderListByUserId(String userId);
	// 캘린더 하나 불러오기
	Calander selectCalanderOne(@Param("userId") String userId,@Param("date") String date);
	// 캘린더 항목 등록
	void insertCalander(Calander calander);
	// 캘린더 항목 수정
	void updateCalander(Calander calander);
	// 캘린더 항목 삭제
	void deleteCalander(@Param("userId") String userId,@Param("date") String date);
}

package com.ssafit.model.service;

import java.util.List;

import com.ssafit.model.dto.Calander;

public interface CalanderService {
	// 유저 id로 캘린더 등록 목록 불러오기
	List<Calander> getCalanderListByUserId(String userId);
	// 캘린더 하나 불러오기
	Calander getCalanderOne(String userId, String date);
	// 캘린더 항목 등록
	void writeCalander(Calander calander);
	// 캘린더 항목 수정
	// 먹은 칼로리 수정	
	void modifyEatCalories(Calander calander);
	// 사용한 칼로리 수정	
	void modifyUseCalories(Calander calander);
	// 캘린더 항목 삭제
	void removeCalander(String userId, String date);
}

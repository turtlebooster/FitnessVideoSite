package com.ssafit.model.service;

import java.util.List;

import com.ssafit.model.dto.Calendar;

public interface CalendarService {
	// 유저 id로 캘린더 등록 목록 불러오기
	List<Calendar> getCalendarListByUserId(String userId);
	// 캘린더 하나 불러오기
	Calendar getCalendarOne(String userId, String date);
	// 캘린더 항목 등록
	void writeCalendar(Calendar Calendar);
	// 캘린더 항목 수정	
	void modifyCalories(Calendar Calendar);
	// 먹은 칼로리 수정	
	void modifyEatCalories(Calendar Calendar);
	// 사용한 칼로리 수정	
	void modifyUseCalories(Calendar Calendar);
	// 캘린더 항목 삭제
	void removeCalendar(String userId, String date);
}

package com.ssafit.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafit.model.dto.Calendar;

public interface CalendarDao {
	// 유저 id로 캘린더 등록 목록 불러오기
	List<Calendar> selectCalendarListByUserId(String userId);
	// 캘린더 하나 불러오기
	Calendar selectCalendarOne(@Param("userId") String userId,@Param("date") String date);
	// 캘린더 항목 등록
	void insertCalendar(Calendar Calendar);
	// 캘린더 항목 수정
	void updateCalendar(Calendar Calendar);
	// 캘린더 항목 삭제
	void deleteCalendar(@Param("userId") String userId,@Param("date") String date);
}

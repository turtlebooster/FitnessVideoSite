package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.CalendarDao;
import com.ssafit.model.dto.Calendar;

@Service
public class CalendarServiceImpl implements CalendarService {
	
	@Autowired
	private CalendarDao CalendarDao;
	
	@Override
	public List<Calendar> getCalendarListByUserId(String userId) {
		return CalendarDao.selectCalendarListByUserId(userId);
	}

	@Override
	public Calendar getCalendarOne(String userId, String date) {
		return CalendarDao.selectCalendarOne(userId, date);
	}

	@Override
	public void writeCalendar(Calendar Calendar) {		
		CalendarDao.insertCalendar(Calendar);
	}

	@Override
	public void modifyCalories(Calendar Calendar) {
		Calendar origin = CalendarDao.selectCalendarOne(Calendar.getUserId(), Calendar.getDate());
		origin.setEatCalories(Calendar.getEatCalories());
		origin.setUseCalories(Calendar.getUseCalories());
		CalendarDao.updateCalendar(origin);
	}
	
	@Override
	public void modifyEatCalories(Calendar Calendar) {
		Calendar origin = CalendarDao.selectCalendarOne(Calendar.getUserId(), Calendar.getDate());
		origin.setEatCalories(Calendar.getEatCalories());
		CalendarDao.updateCalendar(origin);
	}

	@Override
	public void modifyUseCalories(Calendar Calendar) {
		Calendar origin = CalendarDao.selectCalendarOne(Calendar.getUserId(), Calendar.getDate());
		origin.setUseCalories(Calendar.getUseCalories());
		CalendarDao.updateCalendar(origin);		
	}

	@Override
	public void removeCalendar(String userId, String date) {
		CalendarDao.deleteCalendar(userId, date);
	}

}

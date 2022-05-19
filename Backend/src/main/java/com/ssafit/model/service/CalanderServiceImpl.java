package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.CalanderDao;
import com.ssafit.model.dto.Calander;

@Service
public class CalanderServiceImpl implements CalanderService {
	
	@Autowired
	private CalanderDao calanderDao;
	
	@Override
	public List<Calander> getCalanderListByUserId(String userId) {
		return calanderDao.selectCalanderListByUserId(userId);
	}

	@Override
	public Calander getCalanderOne(String userId, String date) {
		return calanderDao.selectCalanderOne(userId, date);
	}

	@Override
	public void writeCalander(Calander calander) {
		calanderDao.insertCalander(calander);
	}

	@Override
	public void modifyEatCalories(Calander calander) {
		Calander origin = calanderDao.selectCalanderOne(calander.getUserId(), calander.getDate());
		origin.setEatCalories(calander.getEatCalories());
		calanderDao.updateCalander(origin);
	}

	@Override
	public void modifyUseCalories(Calander calander) {
		Calander origin = calanderDao.selectCalanderOne(calander.getUserId(), calander.getDate());
		origin.setUseCalories(calander.getUseCalories());
		calanderDao.updateCalander(origin);		
	}

	@Override
	public void removeCalander(String userId, String date) {
		calanderDao.deleteCalander(userId, date);
	}

}

package com.ssafit.model.dto;

import lombok.Data;

public class Calander {
	private String userId;
	private int todoId;
	private String date;
	private int dayOfWeek; 
	private int eatCalories;
	private int useCalories;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getTodoId() {
		return todoId;
	}
	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public int getEatCalories() {
		return eatCalories;
	}
	public void setEatCalories(int eatCalories) {
		this.eatCalories = eatCalories;
	}
	public int getUseCalories() {
		return useCalories;
	}
	public void setUseCalories(int useCalories) {
		this.useCalories = useCalories;
	}
	
}
/*
user_id varchar(50) PK 
todo_id int AI 
date date PK 
day_of_week int 
eat_calories int 
use_calories int
*/
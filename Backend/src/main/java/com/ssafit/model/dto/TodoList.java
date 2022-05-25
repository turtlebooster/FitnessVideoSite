package com.ssafit.model.dto;


public class TodoList {
	private	int no;
	private	int id;
	private	String todo;
	private boolean check;
	
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTodo() {
		return todo;
	}
	public void setTodo(String todo) {
		this.todo = todo;
	}
	@Override
	public String toString() {
		return "TodoList [no=" + no + ", id=" + id + ", todo=" + todo + ", check=" + check + "]";
	}
	
}
/*
no int AI PK 
id int 
todo varchar(200) 
check tinyint
*/
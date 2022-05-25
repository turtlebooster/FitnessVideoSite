package com.ssafit.model.service;

import java.util.List;

import com.ssafit.model.dto.TodoList;

public interface TodoListService {
	// 전체 리스트 불러오기
	List<TodoList> getTodoListList(int id);
	// 하나 불러오기
	TodoList getTodoListOne(int no);
	// todo 만들기
	List<TodoList> writeTodoList(TodoList todoList);	
	// todo 수정
	// 내용 수정
	void modifyTodoList(TodoList todoList);
	// true false 체크
	void checkTodoList(TodoList todoList);
	// todo 삭제
	void deleteTodoList(int no);
}

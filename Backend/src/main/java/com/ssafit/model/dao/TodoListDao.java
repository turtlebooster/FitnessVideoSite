package com.ssafit.model.dao;

import java.util.List;

import com.ssafit.model.dto.TodoList;

public interface TodoListDao {
	// 전체 리스트 불러오기
	List<TodoList> selectTodoListList(int id);
	// 하나 불러오기
	TodoList selectTodoListOne(int no);
	// todo 만들기
	void insertTodoList(TodoList todoList);
	// todo 수정
	void updateTodoList(TodoList todoList);
	// todo 삭제
	void deleteTodoList(int no);
}

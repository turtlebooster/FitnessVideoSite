package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.TodoListDao;
import com.ssafit.model.dto.TodoList;

@Service
public class TodoListServiceImpl implements TodoListService{
	
	@Autowired
	TodoListDao todoListDao;
	
	@Override
	public List<TodoList> getTodoListList(int id) {
		return todoListDao.selectTodoListList(id);
	}

	@Override
	public TodoList getTodoListOne(int no) {
		return todoListDao.selectTodoListOne(no);
	}

	@Override
	public List<TodoList> writeTodoList(TodoList todoList) {
		todoListDao.insertTodoList(todoList);			
		return todoListDao.selectTodoListList(todoList.getId());		
	}

	@Override
	public void modifyTodoList(TodoList todoList) {
		TodoList origin = todoListDao.selectTodoListOne(todoList.getNo());
		origin.setTodo(todoList.getTodo());
		origin.setCheck(todoList.isCheck());
		todoListDao.updateTodoList(origin);		
	}

	@Override
	public void checkTodoList(TodoList todoList) {
		TodoList origin = todoListDao.selectTodoListOne(todoList.getNo());
		// 해보고 안되면 getCheck로 변경할것
		origin.setCheck(todoList.isCheck());
		todoListDao.updateTodoList(origin);	
	}

	@Override
	public void deleteTodoList(int no) {
		todoListDao.deleteTodoList(no);
	}

}

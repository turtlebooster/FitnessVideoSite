package com.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.model.dto.Review;
import com.ssafit.model.dto.TodoList;
import com.ssafit.model.service.ReviewService;
import com.ssafit.model.service.TodoListService;

@RestController
@RequestMapping("/todo")
public class ApiTodoListController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private TodoListService todoListService;
	
	@GetMapping("/list/{id}")
	public ResponseEntity<List<TodoList>> list(@PathVariable int id){	
		return new ResponseEntity<List<TodoList>>(todoListService.getTodoListList(id),  HttpStatus.OK);
	}
	
	@GetMapping("/gettodo/{no}")
	public ResponseEntity<TodoList> getTodo(@PathVariable int no){		
		return new ResponseEntity<TodoList>(todoListService.getTodoListOne(no),  HttpStatus.OK);
	}
	
	@PostMapping("/write")
	public ResponseEntity<List<TodoList>> write(@RequestBody TodoList todoList){
		return new ResponseEntity<List<TodoList>>(todoListService.writeTodoList(todoList), HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody TodoList todoList){
		System.out.println(todoList.toString());
		todoListService.modifyTodoList(todoList);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}	
	
	@DeleteMapping("/delete/{no}")
	public ResponseEntity<String> delete(@PathVariable int no){
		todoListService.deleteTodoList(no);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);		
	}
	@PutMapping("/check")
	public ResponseEntity<String> check(@RequestBody TodoList todoList){
		todoListService.checkTodoList(todoList);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}

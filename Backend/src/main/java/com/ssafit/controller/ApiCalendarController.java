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

import com.ssafit.model.dto.Calendar;
import com.ssafit.model.dto.Review;
import com.ssafit.model.service.CalendarService;

@RestController
@RequestMapping("/Calendar")
public class ApiCalendarController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private CalendarService CalendarService;
	
	@GetMapping("list/{userId}")
	public ResponseEntity<List<Calendar>> list(@PathVariable String userId){		
		return new ResponseEntity<List<Calendar>>(CalendarService.getCalendarListByUserId(userId),  HttpStatus.OK);
	}
	
	@GetMapping("getone/{userId}/{date}")
	public ResponseEntity<Calendar> getOne(@PathVariable String userId, @PathVariable String date){
		System.out.println(userId);
		System.out.println(date);
		Calendar calendar = CalendarService.getCalendarOne(userId, date);
		if (calendar == null) {
			return new ResponseEntity<Calendar>(calendar,  HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Calendar>(calendar,  HttpStatus.OK);
	}
	
	@PostMapping("/write")
	public ResponseEntity<String> write(@RequestBody Calendar Calendar){
		CalendarService.writeCalendar(Calendar);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateCalories")
	public ResponseEntity<String> updateCalories(@RequestBody Calendar Calendar){
		CalendarService.modifyCalories(Calendar);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateeatcalories")
	public ResponseEntity<String> updateEatCalories(@RequestBody Calendar Calendar){
		CalendarService.modifyEatCalories(Calendar);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}	
	
	@PutMapping("/updateusecalories")
	public ResponseEntity<String> updateUseCalories(@RequestBody Calendar Calendar){
		CalendarService.modifyUseCalories(Calendar);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}	
	
	@DeleteMapping("/delete/{userId}/{date}")
	public ResponseEntity<String> delete(@PathVariable String userId, @PathVariable String date){
		CalendarService.removeCalendar(userId, date);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);		
	}

}

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

import com.ssafit.model.dto.Calander;
import com.ssafit.model.dto.Review;
import com.ssafit.model.service.CalanderService;

@RestController
@RequestMapping("/calander")
public class ApiCalanderController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private CalanderService calanderService;
	
	@GetMapping("list/{userId}")
	public ResponseEntity<List<Calander>> list(@PathVariable String userId){		
		return new ResponseEntity<List<Calander>>(calanderService.getCalanderListByUserId(userId),  HttpStatus.OK);
	}
	
	@GetMapping("getone/{userId}/{date}")
	public ResponseEntity<Calander> getOne(@PathVariable String userId, @PathVariable String date){		
		return new ResponseEntity<Calander>(calanderService.getCalanderOne(userId, date),  HttpStatus.OK);
	}
	
	@PostMapping("/write")
	public ResponseEntity<String> write(Calander calander){
		calanderService.writeCalander(calander);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateeatcalories")
	public ResponseEntity<String> updateEatCalories(@RequestBody Calander calander){
		calanderService.modifyEatCalories(calander);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}	
	
	@PutMapping("/updateusecalories")
	public ResponseEntity<String> updateUseCalories(@RequestBody Calander calander){
		calanderService.modifyUseCalories(calander);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}	
	
	@DeleteMapping("/delete/{userId}/{date}")
	public ResponseEntity<String> delete(@PathVariable String userId, @PathVariable String date){
		calanderService.removeCalander(userId, date);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);		
	}

}

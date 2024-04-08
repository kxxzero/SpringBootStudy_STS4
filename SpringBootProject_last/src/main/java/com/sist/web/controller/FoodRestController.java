package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.FoodDAO;
import com.sist.web.entity.Food;

@RestController
@CrossOrigin(origins ="*")
public class FoodRestController {
	@Autowired
	private FoodDAO fDao;
	
	@GetMapping("/food/list/{page}")
	public ResponseEntity<Map> food_list(@PathVariable("page") int page) {
		Map map=new HashMap();
		try {
			int rowSize=20;
			int start=(rowSize*page)-rowSize;
			List<Food> fList=fDao.foodListData(start);
			int count=(int)fDao.count();
			map.put("fList", fList);
			map.put("count", count);
			map.put("curpage", page);
			
		} catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	// ResponseEntity : 오류 확인 목적
	@PostMapping("/food/find/{page}/{address}")
	public ResponseEntity<Map> food_find(@PathVariable("page") int page, @PathVariable("address") String address) {
		Map map=new HashMap();
		try {
			int rowSize=20;
			int start=(rowSize*page)-rowSize;
			List<Food> fList=fDao.foodFindData(address,start);
			int count=fDao.foodFindCount(address);
			map.put("fList", fList);
			map.put("count", count);
			map.put("curpage", page);
		} catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@GetMapping("/food/detail/{fno}")
	public ResponseEntity<Food> food_detail(@PathVariable("fno") int fno) {
		Food food=new Food();
		try {
			food=fDao.findByFno(fno);
		} catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		return new ResponseEntity<>(food, HttpStatus.OK);
	}
}

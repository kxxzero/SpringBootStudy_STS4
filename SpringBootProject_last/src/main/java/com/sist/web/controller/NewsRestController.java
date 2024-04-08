package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import com.sist.web.entity.NewsVO;
import com.sist.web.manager.NewsManager;

@RestController
@CrossOrigin(origins = "*")
public class NewsRestController {

	@Autowired
	private NewsManager nm;
	
	// News 검색
	@GetMapping("/news/list/{fd}")
	public ResponseEntity<List<NewsVO>> newListData(@PathVariable("fd") String fd) {
		List<NewsVO> list=new ArrayList<NewsVO>();
		try {
			list=nm.newsFind(fd);
		} catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}

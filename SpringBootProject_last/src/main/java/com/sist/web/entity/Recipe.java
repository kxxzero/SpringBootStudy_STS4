package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/*
no bigint 
title varchar(2000) 
poster varchar(500) 
chef varchar(200) 
hit bigint
 */
@Entity (name="recipe2")
@Data
public class Recipe {
	@Id
	private int no;
	private String title, poster, chef;
	private int hit;
}

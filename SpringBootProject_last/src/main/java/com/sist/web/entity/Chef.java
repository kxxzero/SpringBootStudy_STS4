package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Chef {
	@Id
	private int cno;
	private String chef, poster;
	private int mem_con1, mem_con2, mem_cont3, mem_con7;
}

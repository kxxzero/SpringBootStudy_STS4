package com.sist.web.entity;

import java.util.Date;

import lombok.Data;

@Entity
@Data
public class Emp {
	@Id
	private int empno;
	private int sal, deptno, mgr;
	private String ename, job, hiredate;
}


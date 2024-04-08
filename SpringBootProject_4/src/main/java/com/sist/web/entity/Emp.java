package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Emp {
    @Id
    private int empno;
    private int sal, deptno, mgr;
    private String ename, job, hiredate;
}

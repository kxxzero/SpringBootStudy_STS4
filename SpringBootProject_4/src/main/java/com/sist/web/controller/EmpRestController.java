package com.sist.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.Emp;
import com.sist.web.dao.EmpDAO;

@RestController
public class EmpRestController {
    private EmpDAO dao;

    @Autowired
    public EmpRestController(EmpDAO dao) {
        this.dao = dao;
    }

    @GetMapping("/emp/list")
    public ResponseEntity<List<Emp>> empAllData() {
        List<Emp> list = new ArrayList<Emp>();
        try {
            list = dao.findAll();
        } catch (Exception ex) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/emp/detail/{empno}") // ~/7788
    public ResponseEntity<Emp> empDetailData(@PathVariable("empno") int empno) {
        Emp emp = new Emp();

        try {
            // detail 정보를 가져오는 코드 작성
        } catch (Exception ex) {
            return new ResponseEntity<>(new Emp(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }
}

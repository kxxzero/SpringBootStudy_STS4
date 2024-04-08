package com.sist.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sist.web.vo.EmpVO;

@Mapper
public interface EmpMapper {
	public List<EmpVO> empListData();
}

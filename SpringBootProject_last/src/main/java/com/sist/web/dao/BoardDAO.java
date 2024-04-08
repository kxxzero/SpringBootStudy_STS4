package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.Board;

public interface BoardDAO extends JpaRepository<Board, Integer>{
	// 목록
	@Query(value="SELECT * FROM jpaboard "
			+ "ORDER BY no DESC "
			+ "LIMIT :start, 10", nativeQuery=true)
	public List<Board> boardListData(@Param("start") int start);
	
	// 총 페이지 => count() 함수 사용
	// Delete => delete
	// 작성(Insert) / 수정(Update) => save() 함수 사용
	
	// 상세보기
	public Board findByNo(int no);
}

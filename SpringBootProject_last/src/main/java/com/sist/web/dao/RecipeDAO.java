package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.Recipe;

public interface RecipeDAO extends JpaRepository<Recipe, Integer>{
	@Query(value="SELECT * FROM recipe2 "
			+ "ORDER BY no ASC "
			+ "LIMIT 0, 5", nativeQuery=true)
	public List<Recipe> recipeMainListData();
	
	@Query(value="SELECT * FROM recipe2 "
			+ "ORDER BY no ASC "
			+ "LIMIT :start, 12", nativeQuery=true)
	public List<Recipe> recipeListData(@Param("start") int start);
	
	// 상세보기
	public Recipe findByNo(int no);
	
	/* public List<Recipe> findByTitleContaining(String title); */
	
	@Query(value="SELECT * FROM recipe2 "
			+ "WHERE title LIKE CONCAT('%', :title, '%') "
			+ "ORDER BY no ASC "
			+ "LIMIT :start, 20", nativeQuery=true)
	public List<Recipe> recipeFindData(@Param("start") int start, @Param("title") String title);
	
	@Query(value="SELECT COUNT(*) FROM recipe2 "
			+ "WHERE title LIKE CONCAT('%', :title, '%')", nativeQuery=true)
	public int recipeFindCount(@Param("title") String title);
	
}

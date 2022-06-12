package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
	@Query("from Movie as m where m.category =:catid")
	public List<Movie> findByCategoryid(@Param("catid")Integer catid);
	
	public List<Movie> findByName(String name);
	
	public void deleteByNameAndLanguage(String name,String language);
	

}

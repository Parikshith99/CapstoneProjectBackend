package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	public List<Category> findByCatname(String catname);
	
	public void deleteByCatname(String catname);
	
	
}

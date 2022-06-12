package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.dao.MovieRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.Category;
import com.example.demo.entity.Movie;
import com.example.demo.entity.User;
import com.example.demo.model.MovieGeneralResponse;

@RestController
@CrossOrigin(origins = "*")
public class HomeController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MovieRepository movieRepository;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user) {
		User userDetails = userRepository.getUserByUserName(user.getEmail());
		if (userDetails != null) {
			return ResponseEntity.ok(false);
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole("ROLE_USER");
		userRepository.save(user);
		return ResponseEntity.ok(true);
	}

	@GetMapping("/general")
	public ResponseEntity<?> general() {
		List<MovieGeneralResponse> allMovies = new ArrayList<MovieGeneralResponse>();
		List<Movie> movies = movieRepository.findAll();
		
		for (Movie m : movies) {
			allMovies.add(
					new MovieGeneralResponse(m.getId(), m.getName(), m.getPrice(), m.getLanguage(), m.getDescription(),
							m.getDate(),m.getTime(), m.getImageurl(), m.getCategory().getCatid(), m.getCategory().getCatname()));
		}
		return new ResponseEntity<List<MovieGeneralResponse>>(allMovies, HttpStatus.OK);
	}

	@GetMapping("/{catname}")
	public ResponseEntity<?> category(@PathVariable("catname") String catname) {
		List<MovieGeneralResponse> categoryMovies = new ArrayList<MovieGeneralResponse>();
		List<Category> catMovies = categoryRepository.findByCatname(catname);

		for (Category c : catMovies) {
			for (Movie mv : c.getMovies()) {
				categoryMovies.add(new MovieGeneralResponse(mv.getId(), mv.getName(), mv.getPrice(), mv.getLanguage(),
						mv.getDescription(), mv.getDate(),mv.getTime(), mv.getImageurl(), mv.getCategory().getCatid(),
						mv.getCategory().getCatname()));
			}

		}
		return new ResponseEntity<List<MovieGeneralResponse>>(categoryMovies, HttpStatus.OK);
	}

	@GetMapping("/search/{query}")
	public ResponseEntity<?> search(@PathVariable("query") String query) {
		List<MovieGeneralResponse> Movies = new ArrayList<MovieGeneralResponse>();
		List<Movie> searchedmovies = movieRepository.findByName(query);
		for (Movie m : searchedmovies) {
			Movies.add(
					new MovieGeneralResponse(m.getId(), m.getName(), m.getPrice(), m.getLanguage(), m.getDescription(),
							m.getDate(),m.getTime(), m.getImageurl(), m.getCategory().getCatid(), m.getCategory().getCatname()));
		}
		return new ResponseEntity<List<MovieGeneralResponse>>(Movies, HttpStatus.OK);
	}

	@GetMapping("/singlemovie/{id}")
	public ResponseEntity<?> search(@PathVariable("id") Integer id) {
		Movie m = movieRepository.getById(id);
		MovieGeneralResponse Movie = new MovieGeneralResponse(m.getId(), m.getName(), m.getPrice(), m.getLanguage(),
				m.getDescription(), m.getDate(),m.getTime(), m.getImageurl(), m.getCategory().getCatid(),
				m.getCategory().getCatname());

		return new ResponseEntity<MovieGeneralResponse>(Movie, HttpStatus.OK);
	}

}

package com.example.demo.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.dao.MovieRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.Category;
import com.example.demo.entity.Movie;
import com.example.demo.entity.User;
import com.example.demo.helper.FileUpload;
import com.example.demo.model.JwtRequest;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FileUpload fileUpload;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/add-movies")
	public ResponseEntity<?> addMovies(@RequestBody ObjectNode objectNode) {

		try {
			List<Category> findByCatname = categoryRepository.findByCatname(objectNode.get("catname").asText());
			if (findByCatname.isEmpty()) {
				Category category = new Category();
				Movie movie = new Movie();
				movie.setName(objectNode.get("name").asText());
				movie.setPrice(objectNode.get("price").asInt());
				movie.setDate(objectNode.get("date").asText());
				movie.setTime(objectNode.get("time").asText());
				movie.setLanguage(objectNode.get("language").asText());
				movie.setDescription(objectNode.get("description").asText());
				movie.setImageurl(objectNode.get("image").asText());

				category.setCatname(objectNode.get("catname").asText());
				movie.setCategory(category);
				category.getMovies().add(movie);

				categoryRepository.save(category);
			} else {
				Category category = findByCatname.get(0);
				Movie movie = new Movie();
				movie.setName(objectNode.get("name").asText());
				movie.setPrice(objectNode.get("price").asInt());
				movie.setDate(objectNode.get("date").asText());
				movie.setTime(objectNode.get("time").asText());
				movie.setLanguage(objectNode.get("language").asText());
				movie.setDescription(objectNode.get("description").asText());
				movie.setImageurl(objectNode.get("image").asText());

				category.setCatname(objectNode.get("catname").asText());
				movie.setCategory(category);
				category.getMovies().add(movie);

				categoryRepository.save(category);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.ok(false);
		}
		return ResponseEntity.ok(true);
	}

	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
		try {

			if (file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("should contain file");
			}
			if (!file.getContentType().equals("image/png") || !file.getContentType().equals("image/jpg") ) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not supported");
			}

			// file upload
			boolean f = fileUpload.uploadFile(file);
			if (f) {
				return ResponseEntity.ok(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}



		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");

	}

	@PostMapping("/login")
	public ResponseEntity<?> adminLogin(@RequestBody JwtRequest jwtRequest) throws Exception {

		try {
			
			User admin = userRepository.findByEmailAndRole(jwtRequest.getUsername(), "ROLE_ADMIN");
			
			if (admin != null && passwordEncoder.matches(jwtRequest.getPassword(), admin.getPassword())) {
				return ResponseEntity.ok(true);
			}

		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("user not found");
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad credentials");
		}

		return ResponseEntity.ok(false);

	}

	@PutMapping("/editMovies/{id}")
	public ResponseEntity<?> editMovies(@RequestBody ObjectNode objectNode, @PathVariable("id") Integer id) {

		try {

			Movie movie = movieRepository.getById(id);
			movie.setName(objectNode.get("name").asText());
			movie.setPrice(objectNode.get("price").asInt());
			movie.setDate(objectNode.get("date").asText());
			movie.setTime(objectNode.get("time").asText());
			movie.setLanguage(objectNode.get("language").asText());
			movie.setDescription(objectNode.get("description").asText());

			movieRepository.save(movie);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.ok(false);
		}
		return ResponseEntity.ok(true);
	}

	@DeleteMapping("/remove-category/{catname}")
	@Transactional
	public ResponseEntity<?> removeCategory(@PathVariable("catname") String catname) {

		try {

			categoryRepository.deleteByCatname(catname);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.ok(false);
		}
		return ResponseEntity.ok(true);
	}

	@GetMapping("/getAllCategory")
	public ResponseEntity<?> getAllCategory() {

		try {

			List<Category> allCategory = categoryRepository.findAll();

			return new ResponseEntity<List<Category>>(allCategory, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.ok(false);
		}

	}

	@DeleteMapping("/deleteMovie/{name}/{language}")
	@Transactional
	public ResponseEntity<?> removeMovies(@PathVariable("name") String name,
			@PathVariable("language") String language) {

		try {
			movieRepository.deleteByNameAndLanguage(name, language);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.ok(false);
		}
		return ResponseEntity.ok(true);
	}

}

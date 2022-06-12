package com.example.demo.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CartRepository;
import com.example.demo.dao.MovieRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Movie;
import com.example.demo.entity.User;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {
	

	@Autowired
	UserRepository userRepository;
	
    @Autowired
	CartRepository cartRepository;
    
    @Autowired
    MovieRepository movieRepository;
    
	
	@GetMapping("/getusers")
	public ResponseEntity<?> getusers(Principal principal) {
		String name = principal.getName();
		User user = userRepository.getUserByUserName(name);
		return ResponseEntity.ok(new User(user.getId(),user.getName(),user.getEmail(),user.getPassword(),user.getRole()));
	}
	
	@PostMapping("/addToCart/{id}")
	public ResponseEntity<?> addToCart(@PathVariable("id") Integer id,Principal principal) {
		try {
			String name = principal.getName();
			User user = userRepository.getUserByUserName(name);
			Movie m = movieRepository.getById(id);
			Cart addcart = new Cart(m.getId(), m.getName(), m.getPrice(), m.getLanguage(),
					m.getDescription(), m.getDate(), m.getImageurl(), m.getCategory().getCatid(),
					m.getCategory().getCatname());
			user.getCarts().add(addcart);
			addcart.setUser(user);
			
			userRepository.save(user);
			return ResponseEntity.ok(true);
		}catch (Exception e) {
			return ResponseEntity.ok(false);
		}
	}
	@GetMapping("/getCartCount")
	public ResponseEntity<?> getCartCount(Principal principal) {
		String name = principal.getName();
		User user = userRepository.getUserByUserName(name);
		List<Cart> carts = user.getCarts();
		return new ResponseEntity<Integer>(carts.size(), HttpStatus.OK);
	}
	
	@GetMapping("/getUser")
	public ResponseEntity<?> getUser(Principal principal) {
		Map <String ,String>m=new HashMap<String,String>();
		String name = principal.getName();
		User user = userRepository.getUserByUserName(name);
		m.put("data", user.getName());
		return new ResponseEntity<Map <String ,String>>(m, HttpStatus.OK);
	}
	@GetMapping("/getCart")
	public ResponseEntity<?> getCart(Principal principal) {
		String name = principal.getName();
		User user = userRepository.getUserByUserName(name);
		List<Cart> carts = user.getCarts();
		
		return new ResponseEntity<List<Cart>>(carts, HttpStatus.OK);
	}
}

package fr.hatclic.chatop.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.hatclic.chatop.model.Users;
import fr.hatclic.chatop.service.UsersService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UsersService usersService;

	@PostMapping("/register")
	@ResponseBody
	public HashMap<String, String> createUser(@RequestBody Users user) {
		usersService.createUser(user);
		HashMap<String, String> map = new HashMap<>();
		map.put("token", "jwt");
		return map;
	}

//	@PostMapping("/login")	
//	@PostMapping("/me")

}

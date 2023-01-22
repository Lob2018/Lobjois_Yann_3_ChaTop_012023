package fr.hatclic.chatop.controller;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.hatclic.chatop.model.Users;
import fr.hatclic.chatop.service.UsersService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "The auth API. Contains all the operations that can be performed on a authentication.")
public class AuthController {

	@Autowired
	private UsersService usersService;

	@PostMapping("/register")
	@ResponseBody	
	public HashMap<String, String> register(@RequestBody Users user) {
		final HashMap<String, String> map = new HashMap<>();
		try {
			user.setCreated_at(ZonedDateTime.now());
			user.setUpdated_at(ZonedDateTime.now());
			usersService.createUser(user);
			map.put("token", "jwt");
			return map;
//			return ResponseEntity.ok(map.toString());
		} catch (Error ex) {
			return map;
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PostMapping("/login")
	@ResponseBody
	public HashMap<String, String> login(@RequestBody Users user) {
		final Optional<Users> userFound = usersService.findByEmail(user.getEmail());
		final HashMap<String, String> map = new HashMap<>();
		if (userFound.isPresent()) {
			if (userFound.get().getPassword().equals(user.getPassword())) {
				map.put("token", "jwt");
				return map;
			}
		}
		map.put("message", "error");
		return map;
	}

	@GetMapping("/me")
	@SecurityRequirement(name = "bearerAuth")
	public HashMap<String, String> me(@RequestHeader(name = "Authorization") String token) {
		// header have got a token
		// the token is valid
		// get the user mail from the token
		// return the user's data else empty JSON
		HashMap<String, String> map = new HashMap<>();
		map.put("token", "jwt");
		return map;
	}
}

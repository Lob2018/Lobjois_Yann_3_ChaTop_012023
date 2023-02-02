package fr.hatclic.chatop.controller;

import java.util.HashMap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.hatclic.chatop.dto.UsersMiniDto;
import fr.hatclic.chatop.model.Users;
import fr.hatclic.chatop.service.UsersService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/user")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Users", description = "The users API. Contains all the operations that can be performed on a user.")
public class UsersController {

	@Autowired
	private UsersService usersService;

	@Autowired
	private ModelMapper modelMapper;

	private final UsersMiniDto convertToDto(final Users user) {
		final UsersMiniDto userDto = modelMapper.map(user, UsersMiniDto.class);
		return userDto;
	}

	/**
	 * Get user informations by id
	 * 
	 * @param id The user id
	 * @return The HTTP response
	 */
	@GetMapping("/{userId}")
	public final ResponseEntity<Object> getUserAccount(
			@Parameter(description = "The user ID to get") @PathVariable("userId") Long id) {
		try {
			final UsersMiniDto userDto = convertToDto(usersService.findUserById(id).get());
			return ResponseEntity.ok().body(userDto);
		} catch (Error ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HashMap<>());
		}
	}
}

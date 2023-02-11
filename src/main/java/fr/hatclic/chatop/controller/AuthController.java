package fr.hatclic.chatop.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.hatclic.chatop.dto.UserRegisterDto;
import fr.hatclic.chatop.dto.UsersLoginDto;
import fr.hatclic.chatop.dto.UsersMiniDto;
import fr.hatclic.chatop.model.Users;
import fr.hatclic.chatop.security.JwtTokenUtil;
import fr.hatclic.chatop.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "The auth API. Contains all the operations that can be performed on a authentication.")
public class AuthController {

	@Autowired
	private UsersService usersService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private ModelMapper modelMapper;

	private final UsersMiniDto convertToDto(final Users user) {
		return modelMapper.map(user, UsersMiniDto.class);
	}

	private final Users convertToEntity(final UserRegisterDto userRegisterDto) {
		return modelMapper.map(userRegisterDto, Users.class);
	}

	/**
	 * Register a new user
	 * 
	 * @param user  The user credentials to register
	 * @param token The corresponding token
	 * @return The HTTP response
	 * @throws MethodArgumentNotValidException Exception for invalid user name
	 */
	@PostMapping("/register")
	@ResponseBody
	@Operation(description = "Register a user")
	@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "object", defaultValue = "{\r\n"
			+ "  \"token\": \"jwt\"\r\n" + "}")), responseCode = "200")
	@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "object", defaultValue = "{}")), responseCode = "400", description = "Bad Request")
	public final ResponseEntity<Object> register(@RequestBody @Valid UserRegisterDto userRegistering) {
		final HashMap<String, String> map = new HashMap<>();
		try {
			final Users user = convertToEntity(userRegistering);
			final String plainPassword = user.getPassword();
			usersService.createUser(user);
			// Get and return the new token
			final Authentication authenticate = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), plainPassword));
			final User autendicatedUser = (User) authenticate.getPrincipal();
			final String token = jwtTokenUtil.generateAccessToken(autendicatedUser);
			map.put("token", token);
			return ResponseEntity.ok().body(map);
		} catch (Error ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<>());
		}
	}

	/**
	 * Get user informations
	 * 
	 * @paramtoken The headers token storing the email
	 * @return The HTTP response
	 */
	@GetMapping("/me")
	@Operation(description = "Get user information")
	@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsersMiniDto.class)), responseCode = "200")
	@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "object", defaultValue = "{}")), responseCode = "401", description = "Unauthorized")
	@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "object", defaultValue = "{}")), responseCode = "400", description = "Bad Request")
	@SecurityRequirement(name = "bearerAuth")
	public final ResponseEntity<Object> me(@RequestHeader(name = "Authorization") String token) {
		try {
			final String mail = SecurityContextHolder.getContext().getAuthentication().getName();
			final Optional<Users> user = usersService.findByEmail(mail);
			if (user.isPresent())
				return ResponseEntity.ok().body(convertToDto(user.get()));
			throw new Error();
		} catch (Error ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HashMap<>());
		}
	}

	/**
	 * User login
	 * 
	 * @param user User login data
	 * @return The HTTP response
	 * @throws MethodArgumentNotValidException
	 */
	@PostMapping("/login")
	@Operation(description = "User login")
	@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "object", defaultValue = "{\r\n"
			+ "  \"token\": \"jwt\"\r\n" + "}")), responseCode = "200")
	@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "object", defaultValue = "{\r\n"
			+ "  \"message\": \"error\"\r\n" + "}")), responseCode = "401", description = "Unauthorized")
	@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "object", defaultValue = "{}")), responseCode = "400", description = "Bad Request")
	public final ResponseEntity<Object> login(@RequestBody @Valid UsersLoginDto user) {
		final HashMap<String, String> map = new HashMap<>();
		try {
			final String email = user.getLogin() == null ? user.getEmail() : user.getLogin();
			// login for Postman and email for Angular -> manual validation
			if (email.trim().length() == 0) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
			}
			final Authentication authenticate = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(email, user.getPassword()));
			final User autendicatedUser = (User) authenticate.getPrincipal();
			final String token = jwtTokenUtil.generateAccessToken(autendicatedUser);
			map.put("token", token);
			return ResponseEntity.ok().body(map);
		} catch (BadCredentialsException ex) {
			map.put("message", "error");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
		}
	}

	/**
	 * Handles @Valid Exceptions (ex. empty email or password)
	 * 
	 * @param ex Spring Boot throwned exception, when the target argument
	 *           annotated @Valid fails to pass the Hibernate Validator
	 * @return Empty JSON
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		return new HashMap<>();
	}
}

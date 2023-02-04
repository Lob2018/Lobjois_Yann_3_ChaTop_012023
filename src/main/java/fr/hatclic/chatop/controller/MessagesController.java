package fr.hatclic.chatop.controller;

import java.util.HashMap;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import fr.hatclic.chatop.dto.MessagesDto;
import fr.hatclic.chatop.model.Messages;
import fr.hatclic.chatop.service.MessagesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/messages")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Messages", description = "The messages API. Contains all the operations that can be performed on a message.")
public class MessagesController {

	@Autowired
	private MessagesService messagesService;

	@Autowired
	private ModelMapper modelMapper;

	private final String messageRequestBodyDescription = "<b>message</b> : The message to send for the rental<br />"
			+ "<b>rental_id</b> &nbsp;: The rental ID<br />" + "<b>user_id</b> &nbsp;&nbsp;&nbsp;: The sender ID<br />";

	private final Messages convertToEntity(final MessagesDto messageDto) {
		final Messages message = modelMapper.map(messageDto, Messages.class);
		return message;
	}

	/**
	 * Send a message to a user for his rental
	 * 
	 * @param message The message to send
	 * @return The HTTP response
	 */
	@PostMapping("/**")
	@ResponseBody
	@Operation(description = "Send a message")
	@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "object", defaultValue = "{\r\n"
			+ "  \"message\": \"Message send with success\"\r\n" + "}")), responseCode = "200")
	@ApiResponse(content = @Content(schema = @Schema(defaultValue = "")), responseCode = "401", description = "Unauthorized")
	@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "object", defaultValue = "{}")), responseCode = "400", description = "Bad Request")
	public final ResponseEntity<Object> message(
			@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = messageRequestBodyDescription) @Valid MessagesDto messageDto) {
		final HashMap<String, Object> map = new HashMap<>();
		try {
			final Messages message = convertToEntity(messageDto);
			messagesService.createMessage(message);
			map.put("message", "Message send with success");
			return ResponseEntity.ok().body(map);
		} catch (Error ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HashMap<>());
		}
	}

	/**
	 * Handles @Valid exceptions (ex. empty user_id or rental_id or message)
	 * 
	 * @param ex Spring Boot throwned exception, when the target argument
	 *           annotated @Valid fails to pass the Hibernate Validator
	 * @return Empty JSON
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final Map<String, String> handleValidationExceptions(final MethodArgumentNotValidException ex) {
		return new HashMap<>();
	}
}
package fr.hatclic.chatop.controller;

import java.time.ZonedDateTime;
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

	private Messages convertToEntity(final MessagesDto messageDto) {
		Messages message = modelMapper.map(messageDto, Messages.class);
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
	public ResponseEntity<Object> message(@RequestBody @Valid MessagesDto messageDto) {
		final HashMap<String, Object> map = new HashMap<>();
		try {
			Messages message = convertToEntity(messageDto);
			message.setCreated_at(ZonedDateTime.now());
			message.setUpdated_at(ZonedDateTime.now());
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
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		return new HashMap<>();
	}
}
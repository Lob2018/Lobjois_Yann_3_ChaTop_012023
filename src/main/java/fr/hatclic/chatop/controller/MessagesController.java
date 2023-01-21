package fr.hatclic.chatop.controller;

import java.time.ZonedDateTime;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.hatclic.chatop.model.Messages;
import fr.hatclic.chatop.service.MessagesService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/messages")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Messages", description = "The messages API. Contains all the operations that can be performed on a message.")
public class MessagesController {

	@Autowired
	private MessagesService messagesService;

	@PostMapping("/")
	@ResponseBody
	public HashMap<String, String> message(@RequestBody Messages message) {
		message.setCreated_at(ZonedDateTime.now());
		message.setUpdated_at(ZonedDateTime.now());
		messagesService.createMessage(message);
		HashMap<String, String> map = new HashMap<>();
		map.put("message", "Message send with success");
		return map;
	}
}
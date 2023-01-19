package fr.hatclic.chatop.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.hatclic.chatop.model.Messages;
import fr.hatclic.chatop.service.MessagesService;

@RestController
@RequestMapping("/messages")

public class MessagesController {

	@Autowired
	private MessagesService messagesService;

	@PostMapping("/")
	@ResponseBody
	public HashMap<String, String> createUser(@RequestBody Messages message) {
		messagesService.createMessage(message);
		HashMap<String, String> map = new HashMap<>();
		map.put("message", "Message send with success");
		return map;
	}

}

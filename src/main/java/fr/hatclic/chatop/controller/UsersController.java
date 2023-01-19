package fr.hatclic.chatop.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.hatclic.chatop.model.Users;
import fr.hatclic.chatop.service.UsersService;

@RestController
@RequestMapping("/api/user")
public class UsersController {

	@Autowired
	private UsersService usersService;

	@GetMapping("/{userId}")
	public Users getUserAccount(@PathVariable("userId") Long id, Model model) {
		return usersService.findUserById(id).get();
	}
}

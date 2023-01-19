package fr.hatclic.chatop.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.hatclic.chatop.model.Users;
import fr.hatclic.chatop.service.UsersService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/")
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	Logger logger = LoggerFactory.getLogger(UsersController.class);

	@GetMapping("user/{userId}")
	public Users getUserAccount(@PathVariable("userId") Long id, Model model) {
		System.out.println("l'id est : " + id);
		return usersService.findUserById(id).get();
	}

	@GetMapping("users")
	public Iterable<Users> getTheUsers() {
		System.out.println("dedans ");
		logger.info("dedans");
		usersService.getAllUsers().forEach((user) -> {
			Users u = (Users) user;
			System.out.println("test " + user);
			System.out.println("e.name " + u.getName());
		});
		return usersService.getAllUsers();
	}

}

package fr.hatclic.chatop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.hatclic.chatop.model.Users;
import fr.hatclic.chatop.repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;

	public Users createUser(Users user) {
		return usersRepository.save(user);
	}

	public Optional<Users> findUserById(final Long id) {
		return usersRepository.findById(id);
	}

	public Iterable<Users> getAllUsers() {
		return usersRepository.findAll();
	}

	public Users updateUser(Users user) {
		return usersRepository.save(user);
	}

	public void deleteUserById(final Long id) {
		usersRepository.deleteById(id);
	}

	public Optional<Users> findByEmail(final String email) {
		return usersRepository.findByEmail(email);
	};

}

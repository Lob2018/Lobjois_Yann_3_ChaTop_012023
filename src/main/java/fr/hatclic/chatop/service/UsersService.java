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
		if (userPropertyIsNull(user))
			throw new Error();
		return usersRepository.save(user);
	}

	public Optional<Users> findUserById(final Long id) {
		if (id == null) {
			throw new Error();
		}
		return usersRepository.findById(id);
	}

	public Iterable<Users> getAllUsers() {
		return usersRepository.findAll();
	}

	public Users updateUser(Users user) {
		if (userPropertyIsNull(user) || user.getId() == null)
			throw new Error();
		return usersRepository.save(user);
	}

	public void deleteUserById(final Long id) {
		if (id == null) {
			throw new Error();
		}
		usersRepository.deleteById(id);
	}

	public Optional<Users> findByEmail(final String email) {
		if (email == null || email.trim().length() == 0) {
			throw new Error();
		}
		return usersRepository.findByEmail(email);
	};

	public boolean userPropertyIsNull(Users user) {
		if (user == null || user.getEmail().trim().length() == 0 || user.getName().trim().length() == 0
				|| user.getPassword().trim().length() == 0

		) {
			return true;
		}
		return false;
	}

}

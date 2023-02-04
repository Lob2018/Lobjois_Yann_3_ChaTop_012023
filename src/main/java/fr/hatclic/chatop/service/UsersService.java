package fr.hatclic.chatop.service;

import java.time.ZonedDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.hatclic.chatop.configuration.SecurityConfig;
import fr.hatclic.chatop.model.Users;
import fr.hatclic.chatop.repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private SecurityConfig securityConfig;

	public final Users createUser(final Users user) {
		if (userPropertyIsNull(user))
			throw new Error();
		user.setCreated_at(ZonedDateTime.now());
		user.setUpdated_at(ZonedDateTime.now());
		user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
		return usersRepository.save(user);
	}

	public final Optional<Users> findUserById(final Long id) {
		if (id == null) {
			throw new Error();
		}
		return usersRepository.findById(id);
	}

	public final Iterable<Users> getAllUsers() {
		return usersRepository.findAll();
	}

	public final Users updateUser(final Users user) {
		if (userPropertyIsNull(user) || user.getId() == null)
			throw new Error();
		return usersRepository.save(user);
	}

	public final void deleteUserById(final Long id) {
		if (id == null) {
			throw new Error();
		}
		usersRepository.deleteById(id);
	}

	public final Optional<Users> findByEmail(final String email) {
		if (email == null || email.trim().length() == 0) {
			throw new Error();
		}
		return usersRepository.findByEmail(email);
	};

	public final boolean userPropertyIsNull(final Users user) {
		if (user == null || user.getEmail().trim().length() == 0 || user.getName().trim().length() == 0
				|| user.getPassword().trim().length() == 0) {
			return true;
		}
		return false;
	}

}

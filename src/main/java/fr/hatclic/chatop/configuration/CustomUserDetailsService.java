package fr.hatclic.chatop.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.hatclic.chatop.model.Users;
import fr.hatclic.chatop.repository.UsersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		Optional<Users> user = userRepository.findByEmail(userEmail);

		if (user.isEmpty())
			throw new UsernameNotFoundException(userEmail + " not found");

		// Use the following line if password are not encrypted in database.
		user.get().setPassword(new BCryptPasswordEncoder().encode(user.get().getPassword()));

		return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(),
				getGrantedAuthorities("ROLE_USER"));
	}

	private List<GrantedAuthority> getGrantedAuthorities(String role) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		// Spring Security automatically prefix role with ROLE_
		// so if the role name in database isn't prefix with ROLE_
		// we have to it
		authorities.add(new SimpleGrantedAuthority(role));
		return authorities;
	}

}

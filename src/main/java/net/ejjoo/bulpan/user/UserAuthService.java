package net.ejjoo.bulpan.user;

import net.ejjoo.bulpan.user.model.User;
import net.ejjoo.bulpan.user.model.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * https://www.baeldung.com/registration-with-spring-mvc-and-spring-security
 */

@Service
public class UserAuthService implements UserDetailsService {
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userService.getUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(
					"No user found with username: "+ email);
		}
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		return  new org.springframework.security.core.userdetails.User
				(user.getEmail(),
						user.getPassword().toLowerCase(), enabled, accountNonExpired,
						credentialsNonExpired, accountNonLocked,
						getAuthorities(user.getRoles()));
	}

	public Collection<GrantedAuthority> getAuthorities(Iterable<String> roles) {
		return StreamSupport
				.stream(roles.spliterator(), false)
				.map(r -> new SimpleGrantedAuthority(r))
				.collect(Collectors.toList());
	}


	public void signUp(SignUpDto signUpDto) throws Exception {
		validateSignUpDto(signUpDto);

		User user = new User();
		user.setEmail(signUpDto.getEmail());
		user.setNickname(signUpDto.getNickname());
		user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
		userRepository.save(user);
	}

	private boolean validateSignUpDto(SignUpDto signUpDto) throws Exception {
		if (emailExists(signUpDto.getEmail())) {
			throw new Exception();
		}

		if (!signUpDto.getPassword().equals(signUpDto.getMatchingPassword())) {
			throw new Exception();
		}

		return true;
	}

	public boolean signIn(String email, String password) {
		return authenticate(email, password);
	}

	public boolean emailExists(String email) {
		return userRepository.findFirstByEmail(email) != null;
	}

	public boolean authenticate(String email, String password) {
		User user = userService.getUserByEmail(email);
		if (user == null) {
			return false;
		}

		return passwordEncoder.matches(password, user.getPassword());
	}
}

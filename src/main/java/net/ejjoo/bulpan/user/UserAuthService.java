package net.ejjoo.bulpan.user;

import net.ejjoo.bulpan.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * https://www.baeldung.com/registration-with-spring-mvc-and-spring-security
 */

public class UserAuthService implements UserDetailsService {
	@Autowired
	private UserService userService;

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
}

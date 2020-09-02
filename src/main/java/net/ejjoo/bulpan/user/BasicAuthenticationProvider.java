package net.ejjoo.bulpan.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BasicAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private UserAuthService userAuthService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
		String password = (String) authentication.getCredentials();
		if (userAuthService.authenticate(email, password)) {
			return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), userAuthService.getAuthorities(Arrays.asList()));
		}

		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}

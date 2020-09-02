package net.ejjoo.bulpan.config;

import net.ejjoo.bulpan.user.BasicAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private BasicAuthenticationProvider basicAuthenticationProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(basicAuthenticationProvider);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
//		web.ignoring().antMatchers("/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
				.mvcMatchers("/login*").permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin()
//					.loginPage("/login")
					.loginProcessingUrl("/authenticate")
					.defaultSuccessUrl("/post/list")
			.and()
				.logout()
					.logoutUrl("/logout")
					.deleteCookies("JSESSIONID");
	}

	@Bean
	public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
		return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
	}

	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return super.userDetailsServiceBean();
	}

	private LogoutSuccessHandler logoutSuccessHandler() {
		return null;
	}

	private AuthenticationFailureHandler authenticationFailedHandler() {
		return null;
	}

}

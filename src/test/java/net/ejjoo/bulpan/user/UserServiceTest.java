package net.ejjoo.bulpan.user;

import net.ejjoo.bulpan.user.model.SignUpDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {
	@Autowired
	private UserAuthService userAuthService;

	private SignUpDto signUpDto;
	public UserServiceTest() {
		signUpDto = new SignUpDto();
		signUpDto.setEmail("test@test.com");
		signUpDto.setNickname("test");
		signUpDto.setPassword("test123!@#");
		signUpDto.setMatchingPassword(signUpDto.getPassword());
	}

	@Test
	@Order(1)
	public void signUp() throws Exception {
		userAuthService.signUp(signUpDto);
		Assertions.assertTrue(userAuthService.emailExists(signUpDto.getEmail()));
	}

	@Test
	@Order(2)
	public void signIn() {
		Assertions.assertTrue(userAuthService.signIn(signUpDto.getEmail(), signUpDto.getPassword()));
		Assertions.assertFalse(userAuthService.signIn(signUpDto.getEmail(), signUpDto.getPassword() + "1"));
	}
}
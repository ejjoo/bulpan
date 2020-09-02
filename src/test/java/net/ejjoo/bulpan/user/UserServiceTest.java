package net.ejjoo.bulpan.user;

import net.ejjoo.bulpan.user.model.UserSignUpDto;
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

	private UserSignUpDto userSignUpDto;
	public UserServiceTest() {
		userSignUpDto = new UserSignUpDto();
		userSignUpDto.setEmail("test@test.com");
		userSignUpDto.setNickname("test");
		userSignUpDto.setPassword("test123!@#");
		userSignUpDto.setMatchingPassword(userSignUpDto.getPassword());
	}

	@Test
	@Order(1)
	public void signUp() throws Exception {
		userAuthService.signUp(userSignUpDto);
		Assertions.assertTrue(userAuthService.emailExists(userSignUpDto.getEmail()));
	}

	@Test
	@Order(2)
	public void signIn() {
		Assertions.assertTrue(userAuthService.signIn(userSignUpDto.getEmail(), userSignUpDto.getPassword()));
		Assertions.assertFalse(userAuthService.signIn(userSignUpDto.getEmail(), userSignUpDto.getPassword() + "1"));
	}
}
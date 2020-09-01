package net.ejjoo.bulpan.user;

import net.ejjoo.bulpan.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
	@Autowired
	private UserRepository userRepository;

	public User getUserByEmail(String email) {
		return null;
	}
}

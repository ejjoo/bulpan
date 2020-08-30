package net.ejjoo.bulpan.user;

import net.ejjoo.bulpan.user.validator.ValidEmail;

public class UserDto {
	@ValidEmail
	private String email;

	private String password;

	private String nickname;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}

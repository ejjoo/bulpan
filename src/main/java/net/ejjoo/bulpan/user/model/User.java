package net.ejjoo.bulpan.user.model;

import net.ejjoo.bulpan.user.validator.ValidEmail;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Proxy
public class User {
	@Id
	@GeneratedValue
	private int id;

	@Column(length = 256)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(unique = true, nullable = false, length = 16)
	private String nickname;

	@CreationTimestamp
	private LocalDateTime createDtm;

	@UpdateTimestamp
	private LocalDateTime updateDtm;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public LocalDateTime getCreateDtm() {
		return createDtm;
	}

	public void setCreateDtm(LocalDateTime createDtm) {
		this.createDtm = createDtm;
	}

	public LocalDateTime getUpdateDtm() {
		return updateDtm;
	}

	public void setUpdateDtm(LocalDateTime updateDtm) {
		this.updateDtm = updateDtm;
	}

	//todo: create role scheme, and make a relation for the role table.
//	@OneToMany()
	public Iterable<String> getRoles() {
		return new ArrayList<>();
	}

}

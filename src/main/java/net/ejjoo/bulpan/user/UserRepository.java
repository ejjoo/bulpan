package net.ejjoo.bulpan.user;

import net.ejjoo.bulpan.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findFirstByEmail(String email);
}

package net.ejjoo.bulpan.Repository;

import net.ejjoo.bulpan.Model.Post;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class PostRepositoryTest {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	PostRepository postRepository;

	@Test
	void test() {
		Post post = new Post();
		post.setTitle("test");
		post.setContent("test content");
		postRepository.save(post);
		log.debug(post.toString());
	}
}
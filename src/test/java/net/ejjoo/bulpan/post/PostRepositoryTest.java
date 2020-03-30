package net.ejjoo.bulpan.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	PostRepository postRepository;

	@Test
	@DisplayName("Test")
	void test() {
		Post post = new Post();
		post.setTitle("test");
		post.setContent("test content");
		postRepository.save(post);
		log.debug("test");
		log.debug("id: {}", post.getId());
		log.debug("count: {}", postRepository.count());
		assertNotEquals(postRepository.count(), 0);
	}
}
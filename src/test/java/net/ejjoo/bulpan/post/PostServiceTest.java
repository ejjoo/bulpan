package net.ejjoo.bulpan.post;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("local")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostServiceTest {
	@Autowired
	private PostService postService;

	private final Post testPost;

	private Logger log = LoggerFactory.getLogger(getClass());

	public PostServiceTest() {
		testPost = new Post();
		testPost.setTitle("test");
		testPost.setContent("test content");
	}

	@Test
	@Order(1)
	public void createPost() {
		postService.createPost(testPost);
		Assertions.assertNotEquals(testPost.getId(), 0);
	}

	@Test
	@Order(2)
	public void getList() {
		List<Post> postList = postService.getListAll();
		Assertions.assertNotEquals(postList.size(), 0);
		Assertions.assertTrue(postList.stream().anyMatch(p -> testPost.getId() == p.getId()));
	}

	@Test
	@Order(3)
	public void readPost() {
		Post post = postService.getPost(testPost.getId());
		log.debug("content: {}", post.getContent());
		Assertions.assertEquals(post.getId(), testPost.getId());
		Assertions.assertNotEquals(post.getContent(), "");
	}

	@Test
	@Order(4)
	public void deletePost() {
		postService.deletePost(testPost.getId());
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			postService.getPost(testPost.getId());
		});
	}
}

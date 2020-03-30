package net.ejjoo.bulpan.post;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PostService {
	private final PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public Post createPost(Post post) {
		return postRepository.save(post);
	}

	public List<Post> getListAll() {
		return StreamSupport.stream(postRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public Post getPost(int id) {
		return postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
	}

	public void deletePost(int id) {
		postRepository.deleteById(id);
	}
}

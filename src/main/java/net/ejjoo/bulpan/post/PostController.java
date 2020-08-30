package net.ejjoo.bulpan.post;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("post")
public class PostController {
	public static final String VIEW_LIST = "list";
	public static final String VIEW_FORM = "form";
	public static final String VIEW_POST = "post";

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("form")
	public String form(ModelMap modelMap) {
		return VIEW_FORM;
	}

	@GetMapping("{id}")
	public String itemView(@PathVariable String id, ModelMap modelMap) {
		modelMap.put("post", postService.getPost(Integer.valueOf(id)));
		return VIEW_POST;
	}

	@GetMapping("list")
	public String list(ModelMap modelMap) {
		modelMap.put("list", postService.getListAll());
		return VIEW_LIST;
	}

	@PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public RedirectView post(Post post) {
		postService.createPost(post);
		return new RedirectView("post/list");
	}
}

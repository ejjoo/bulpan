package net.ejjoo.bulpan.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("post")
public class PostController {
	public static final String VIEW_LIST = "list";

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("list")
	public String list(ModelMap modelMap) {
		modelMap.put("list", postService.getListAll());
		return VIEW_LIST;
	}

}

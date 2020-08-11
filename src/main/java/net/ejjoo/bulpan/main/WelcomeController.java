package net.ejjoo.bulpan.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class WelcomeController {
	@GetMapping("/")
	public RedirectView welcome() {
		return new RedirectView("post/list");
	}
}

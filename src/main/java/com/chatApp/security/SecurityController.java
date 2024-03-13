package com.chatApp.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

	@GetMapping("/")
	public String home(Model model, @AuthenticationPrincipal OAuth2User user ) {
		
		model.addAttribute("email", user.getAttribute("email"));
		return "index";
	}
}

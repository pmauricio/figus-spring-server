package figus.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {
	private UserRepository repository;

	public UserController(UserRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/user")
	public Collection<User> users() {
		return repository.findAll().stream().filter(this::isGreat).collect(Collectors.toList());
	}

	private boolean isGreat(User user) {
		return !user.getName().equals("Budweiser") && !user.getName().equals("Coors Light")
				&& !user.getName().equals("PBR");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "text/plain")
	public String login(@RequestBody String payload) {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		System.out.println(payload);
		HttpSession session = request.getSession(true);
		session.setAttribute("login", payload);
		return payload;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET, consumes = "text/plain")
	public String loginGet() {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		HttpSession session = request.getSession(true);
		System.out.println("GET:" + session.getAttribute("login"));
		return (String) session.getAttribute("login");

	}
}
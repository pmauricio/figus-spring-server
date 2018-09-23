package figus.user;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
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

	@GetMapping("/logout")
	public String logout() {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		System.out.println(")logout");
		request.getSession(true).invalidate();
		return "{}";

	}

	@GetMapping("/console")
	public String console() {

		System.out.println("CONSOLE:" + System.getProperty("os.name"));
		boolean isOSX = System.getProperty("os.name").toLowerCase().startsWith("mac");

		ProcessBuilder builder = new ProcessBuilder();
		if (isOSX) {
			System.out.println("is osx");
			builder.command("pwd ");
		} else {
			builder.command("journalctl ", " -u ", "figusserver.service");
		}
		// builder.directory(new File(System.getProperty("user.home")));
		Process process;
		try {
			process = builder.start();

			StringBuffer sb = new StringBuffer("");
			Consumer<String> d = e -> sb.append(e);

			StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), d);
			Executors.newSingleThreadExecutor().submit(streamGobbler);
			int exitCode = process.waitFor();
			assert exitCode == 0;

			return sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
	}

	private static class StreamGobbler implements Runnable {
		private InputStream inputStream;
		private Consumer<String> consumer;

		public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
			this.inputStream = inputStream;
			this.consumer = consumer;
		}

		@Override
		public void run() {
			new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(consumer);
		}
	}
}
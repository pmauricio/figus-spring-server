package figus.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
        return repository.findAll().stream()
                .filter(this::isGreat)
                .collect(Collectors.toList());
    }

    private boolean isGreat(User user) {
        return !user.getName().equals("Budweiser") &&
                !user.getName().equals("Coors Light") &&
                !user.getName().equals("PBR");
    }
   
    @GetMapping("/login")
    public String login() {
   
    	  HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

          HttpSession session = request.getSession(true);
          session.setAttribute("logged_user", repository.findAll().get(0).getName());
          session.setAttribute("logged_user_id", repository.findAll().get(0).getId());
          System.out.println(session.getAttribute("logged_user"));
          
    	return (String) session.getAttribute("logged_user");
    }
}
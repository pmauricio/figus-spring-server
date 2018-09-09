package figus.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

@RestController
public class ClienrtController {
    private ClientRepository repository;

    public ClienrtController(ClientRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/clients")
    public Collection<Client> projects() {
    ;

        System.out.println(	((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("logged_user"));
        return repository.findAll().stream()
                .filter(this::isGreat)
                .collect(Collectors.toList());
    }

    private boolean isGreat(Client project) {
        return !project.getName().equals("Budweiser") &&
                !project.getName().equals("Coors Light") &&
                !project.getName().equals("PBR");
    }
}
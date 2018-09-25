package figus.project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import figus.user.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ProjectController {
	private ProjectRepository repository;

	public ProjectController(ProjectRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/api/projects")
	public Collection<Project> projects() {

	
System.out.println( "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$==========\n"+
	
	
		(((User)((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("login"))))   ;
System.out.println(((User)((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("login")).getActualClient());	
System.out.println( "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$==========");
return ((User) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest().getSession().getAttribute("login")).getActualClient().getProjects();
	}

//	private boolean isGreat(Project project) {
//
//		if (project.getClient() != null) {
//			System.out.println("["+((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//			.getRequest().getSession().getAttribute("login")+"]");
//			return project.getClient()
//					.getId() == ((User) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//							.getRequest().getSession().getAttribute("login")).getActualClient().getId();
//		}else
//			return false;
//	}
}
package figus.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class UserCommandLineRunner implements CommandLineRunner {

    private final UserRepository repository;

    public UserCommandLineRunner(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
      //   Top beers from https://www.beeradvocate.com/lists/top/
        Stream.of("Mauricio").forEach(name ->
                repository.save(new User(name,""))
        );
    	
        repository.findAll().forEach(System.out::println);
      
   }
}
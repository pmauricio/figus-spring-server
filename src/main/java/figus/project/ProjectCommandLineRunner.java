package figus.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class ProjectCommandLineRunner implements CommandLineRunner {

    private final ProjectRepository repository;

    public ProjectCommandLineRunner(ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        // Top beers from https://www.beeradvocate.com/lists/top/
//        Stream.of("The Godfather", "Her", "Bye Bye Brasil", "King Julius",
//                "Better Call Saul", "PBR","la Escondida","la de Mauricio").forEach(name ->
//                repository.save(new Project(name))
//        );
        repository.findAll().forEach(System.out::println);
    }
}
package figus.item;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class ItemCommandLineRunner implements CommandLineRunner {

    private final ItemRepository repository;

    public ItemCommandLineRunner(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        // Top beers from https://www.beeradvocate.com/lists/top/
//        Stream.of("Acervo La Lupa", "Livreria el Sapo", "Produtora K",
//             "Produtora Ozoono","Hollywood").forEach(name ->
//                repository.save(new Client(name))
//        );
        repository.findAll().forEach(System.out::println);
    }
}
package ru.itfb.bookworm.Controller;

import org.springframework.web.bind.annotation.*;
import ru.itfb.bookworm.Entity.Author;
import ru.itfb.bookworm.Repository.AuthorRepository;
import ru.itfb.bookworm.Service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorRepository repository;
    private AuthorService service;

    AuthorController(AuthorRepository repository, AuthorService service) {

        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/get_author")
    List<Author> getAuthorAll() {
        return service.getAuthors();
    }

    @GetMapping("/get_author/{id}")
    Author getAuthor(@PathVariable Long id) {
        return service.getAuthorById(id);
    }

    @GetMapping("/get_author/full_name/{full_name}")
    Author fullName(@PathVariable String full_name) {
       // new Audit();
        return repository.findByFullName(full_name);
    }

    @PostMapping("/add_author")
    Author newAuthor(@RequestBody Author newAuthor) {
        return service.addAuthor(newAuthor);
    }

    @DeleteMapping("/del_author/{id}")
    void deleteAuthor(@PathVariable Long id) {
        service.deleteAuthor(id);
    }
}

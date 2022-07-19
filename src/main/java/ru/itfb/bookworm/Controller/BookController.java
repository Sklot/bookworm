package ru.itfb.bookworm.Controller;

import org.springframework.web.bind.annotation.*;
import ru.itfb.bookworm.Entity.Book;
import ru.itfb.bookworm.Repository.BookRepository;
import ru.itfb.bookworm.Service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository repository;
    private BookService service;

    BookController(BookRepository repository, BookService service) {
        this.repository = repository;
        this.service = service;
    }
    @GetMapping("/get_book")
    List<Book> getBookAll() {
        return service.getBooks();
    }

    @GetMapping("/get_book/{id}")
    Book getBook(@PathVariable Long id) {
        return service.getBookById(id);
    }


    @PostMapping("/add_book")
    Book newBook(@RequestBody Book newBook) {
        return service.addBook(newBook);
    }



    @PutMapping("/update_book/{id}")
    public Book updateBook(@RequestBody Book book) {
        return service.updateBook(book);
    }

    @DeleteMapping("/del_book/{id}")
    void deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
    }
}

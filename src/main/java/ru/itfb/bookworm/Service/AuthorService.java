package ru.itfb.bookworm.Service;

import org.springframework.stereotype.Service;
import ru.itfb.bookworm.AuthorNotFoundException;
import ru.itfb.bookworm.BookNotFoundException;
import ru.itfb.bookworm.Entity.Author;
import ru.itfb.bookworm.Entity.Book;
import ru.itfb.bookworm.Repository.AuthorRepository;
import ru.itfb.bookworm.Repository.BookRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class AuthorService {
private final BookRepository repoBook;
private final AuthorRepository repository;

    public AuthorService(BookRepository repoBook, AuthorRepository repository) {
        this.repoBook = repoBook;
        this.repository = repository;
    }

    public Author addAuthor(Author Author) {
        Author newAuthor = new Author();
        newAuthor.setFull_name(Author.getFull_name());
        newAuthor.setBooks(new ArrayList<Book>());

        List<Book> books = Author.getBooks();
        if (Author.getBooks() == null) {
            System.out.println("нет книг");
        } else {
            Book existBook;
            for (Book el : books) {
                existBook = repoBook.findByNameIgnoreCase(el.getName());
                if (existBook != null) {
                    newAuthor.addBook(existBook);
                } else if (el.getName() != null && !el.getName().equals("")) {
                    Book book = new Book(el.getName());
                    repoBook.save(book);
                    repository.save(newAuthor);
                    newAuthor.addBook(book);
                }
            }
        }
        return repository.save(newAuthor);
    }

    public List<Author> getAuthors() {
        return repository.findAll();
    }

    public Author getAuthorById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException());
    }

    public String deleteAuthor(Long id) {
        repository.deleteById(id);
        return "Author deleted: " + id;
    }
}

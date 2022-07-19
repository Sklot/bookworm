package ru.itfb.bookworm.Service;

import org.springframework.stereotype.Service;
import ru.itfb.bookworm.BookNotFoundException;
import ru.itfb.bookworm.Entity.Author;
import ru.itfb.bookworm.Entity.Book;
import ru.itfb.bookworm.Repository.AuthorRepository;
import ru.itfb.bookworm.Repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository repository;
    private final AuthorRepository repoAuthor;
    public BookService(BookRepository repository, AuthorRepository repoAuthor) {
        this.repoAuthor = repoAuthor;
        this.repository = repository;
    }
    public Book addBook(Book Book) {
        Book newBook = new Book();
        newBook.setName(Book.getName());
        newBook.setGenre(Book.getGenre());
        newBook.setReview(Book.getReview());
        newBook.setAuthors(new ArrayList<Author>());

        List<Author> authors = Book.getAuthors();
        if (Book.getAuthors() == null) {
            System.out.println("нет авторов");
        } else {
            Author existAuthor;
            for (Author el : authors) {
                existAuthor = repoAuthor.findByFullName(el.getFull_name());
                if (existAuthor != null) {
                    newBook.addAuthor(existAuthor);
                } else if (el.getFull_name() != null && !el.getFull_name().equals("")) {
                    Author author = new Author(el.getFull_name());
                    repoAuthor.save(author);
                    repository.save(newBook);
                    newBook.addAuthor(author);
                }
            }
        }
        return repository.save(newBook);
    }

    public List<Book> getBooks() {
        return repository.findAll();
    }
    public Book getBookById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException());
    }

    public Book updateBook(Book Book) {
        Book existBook = repository.findById(Book.getBook_id()).orElseThrow(BookNotFoundException::new);

        existBook.setName(Book.getName() == null ? existBook.getName() : Book.getName());
        existBook.setGenre(Book.getGenre() == null ? existBook.getGenre() : Book.getGenre());
        existBook.setReview(Book.getReview() == null ? existBook.getReview() : Book.getReview());
        existBook.setAuthors(Book.getAuthors() == null ? existBook.getAuthors() : Book.getAuthors());

        List<Author> authors = Book.getAuthors();

        if (Book.getAuthors() == null) {
            System.out.println("нет авторов");
        } else {
            Author existAuthor;
            for (Author el : authors) {
                existAuthor = repoAuthor.findByFullName(el.getFull_name());
                if (existAuthor != null) {
                    existBook.addAuthor(existAuthor);
                } else if (el.getFull_name() != null && !el.getFull_name().equals("")) {
                    Author author = new Author(el.getFull_name());
                    repoAuthor.save(author);
                    repository.save(existBook);
                    existBook.addAuthor(author);
                }
            }
        }
        return repository.save(existBook);
    }

    public String deleteBook(Long id) {
        repository.deleteById(id);
        return "Book deleted: " + id;
    }


}

package ru.itfb.bookworm.Book;

import lombok.Getter;
import lombok.Setter;
import ru.itfb.bookworm.Author.Author;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Book")
public class Book {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "book_id", nullable = false)
    Long book_id;

    @Getter
    @Setter
    @Column
    String name;

    @Getter
    @Setter
    @Column
    private String author;

    @Getter
    @Setter
    @Column
    private String review;

    @ManyToMany
    @JoinTable (name="book_author",
            joinColumns=@JoinColumn (name="book_id"),
            inverseJoinColumns=@JoinColumn(name="author_id"))
    private List<Author> authors;
}

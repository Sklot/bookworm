package ru.itfb.bookworm.Author;

import lombok.Getter;
import lombok.Setter;
import ru.itfb.bookworm.Book.Book;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "author")
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "author_id", nullable = false)
    private Long author_id;

    @Column
    private String full_name;
    @Column
    private String book;

    @ManyToMany
    @JoinTable (name="book_author",
            joinColumns=@JoinColumn (name="author_id"),
            inverseJoinColumns=@JoinColumn(name="book_id"))
    private List<Book> books;

}
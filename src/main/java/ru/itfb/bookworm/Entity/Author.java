package ru.itfb.bookworm.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
@Getter
@Setter
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "author_id", nullable = false)
    private Long author_id;

    @Column
    private String full_name;

    @ManyToMany
    //@JsonIgnore
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "book_id")
    @JoinTable (name="book_author",
            joinColumns=@JoinColumn (name="author_id"),
            inverseJoinColumns=@JoinColumn(name="book_id"))
    private List<Book> books;

    public Author(String full_name){
        this.full_name = full_name;
        this.books = new ArrayList<Book>();
    }

    public void addBook(Book book){
        this.books.add(book);
        book.getAuthors().add(this);
    }
}
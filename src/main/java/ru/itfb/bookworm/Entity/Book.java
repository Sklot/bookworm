package ru.itfb.bookworm.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itfb.bookworm.Entity.Author;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Book")
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "book_id", nullable = false)
    private Long book_id;


    @Column
    private String name;


    @Column
    private String genre;


    @Column
    private String review;

    @ManyToMany
   // @JsonIgnore
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "author_id")
    @JoinTable (name="book_author",
            joinColumns=@JoinColumn (name="book_id"),
            inverseJoinColumns=@JoinColumn(name="author_id"))
    private List<Author> authors;

    public Book(String name){
        this.name = name;
        this.authors = new ArrayList<Author>();
    }

    public void addAuthor(Author author){
        this.authors.add(author);
        author.getBooks().add(this);
    }


    public void removeAuthor(Author author){
        this.authors.remove(author);
        author.getBooks().remove(this);
    }
}

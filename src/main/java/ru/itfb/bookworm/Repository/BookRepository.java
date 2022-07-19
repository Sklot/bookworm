package ru.itfb.bookworm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itfb.bookworm.Entity.Book;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    @Query("select b from Book b where upper(b.name) = upper(?1)")
    Book findByNameIgnoreCase(String name);

}
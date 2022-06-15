package ru.itfb.bookworm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.itfb.bookworm.Book.Book;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
}
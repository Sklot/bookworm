package ru.itfb.bookworm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itfb.bookworm.Author.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
package ru.itfb.bookworm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itfb.bookworm.Entity.Audit;

public interface AuditRepository extends JpaRepository<Audit, Long> {
}
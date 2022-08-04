package ru.kolesnik.sheetposting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolesnik.sheetposting.repository.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}

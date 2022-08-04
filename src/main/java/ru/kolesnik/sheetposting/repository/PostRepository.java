package ru.kolesnik.sheetposting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolesnik.sheetposting.repository.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}

package com.example.Library.repository;

import com.example.Library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findByName(String name);
    @Query("SELECT a FROM Author a WHERE a.name IN :names")
    List<Author> findAuthorsByNames(List<String> names);

}

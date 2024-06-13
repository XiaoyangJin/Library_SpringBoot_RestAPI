package com.example.Library.repository;

import com.example.Library.entity.AuthorBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorBookRepository extends JpaRepository<AuthorBook, Integer> {
}

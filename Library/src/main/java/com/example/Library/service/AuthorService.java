package com.example.Library.service;

import com.example.Library.entity.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    List<Author> findAllAuthors();
}

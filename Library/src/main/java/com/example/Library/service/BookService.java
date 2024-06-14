package com.example.Library.service;

import com.example.Library.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<Book> findAllBooks();
    List<Book> findBooksByTitle(String title);
    List<Book> findBooksByAuthorName(String authorName);
}

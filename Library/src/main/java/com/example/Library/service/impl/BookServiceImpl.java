package com.example.Library.service.impl;

import com.example.Library.entity.Author;
import com.example.Library.entity.AuthorBook;
import com.example.Library.entity.Book;
import com.example.Library.repository.AuthorBookRepository;
import com.example.Library.repository.AuthorRepository;
import com.example.Library.repository.BookRepository;
import com.example.Library.service.BookService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @PersistenceContext
    private EntityManager entityManager;

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final AuthorBookRepository authorBookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, AuthorBookRepository authorBookRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.authorBookRepository = authorBookRepository;
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findBookById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional
    public Book createBook(Book book, List<String> authors) {
        bookRepository.save(book);

        for (String authorName : authors) {
            Author author = findOrCreateAuthor(authorName);
            AuthorBook authorBook = new AuthorBook();
            authorBook.setAuthor(author);
            authorBook.setBook(book);
            authorBookRepository.save(authorBook);
        }

        return book;
    }

    @Override
    @Transactional
    public Book updateBook(int id, Book updatedBook, List<String> authors) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(updatedBook.getTitle());
                    authorBookRepository.deleteAllByBook(existingBook);

                    for (String authorName : authors) {
                        Author author = findOrCreateAuthor(authorName);
                        AuthorBook authorBook = new AuthorBook();
                        authorBook.setAuthor(author);
                        authorBook.setBook(existingBook);
                        authorBookRepository.save(authorBook);
                    }

                    return bookRepository.save(existingBook);
                })
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
    }

    @Override
    @Transactional
    public void deleteBook(int id) {
        authorBookRepository.deleteAllByBookId(id);
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findBooksByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> findBooksByAuthorName(String authorName) {
        return authorBookRepository.findByAuthorName(authorName)
                .stream()
                .map(AuthorBook::getBook)
                .distinct()
                .collect(Collectors.toList());
    }

    private Author findOrCreateAuthor(String authorName) {
        return authorRepository.findByName(authorName)
                .stream()
                .findFirst()
                .orElseGet(() -> {
                    Author author = new Author();
                    author.setName(authorName);
                    return authorRepository.save(author);
                });
    }
}

# Library Management System

## Overview

This project is a Library Management System built with Spring Boot. It provides RESTful APIs to manage authors and books in a PostgreSQL database. The system supports CRUD operations for authors and books and maintains a many-to-many relationship between them, meaning an author can write multiple books, and a book can be written by multiple authors.

## Features

- CRUD operations for authors and books.
- Many-to-many relationship between authors and books.
- RESTful APIs to interact with the database.

## Database Schema

The PostgreSQL database consists of three tables: `authors`, `books`, and `author_books` (join table).

### Authors Table

| Column    | Type        | Description              |
|-----------|-------------|--------------------------|
| author_id | SERIAL      | Primary Key              |
| name      | VARCHAR(100)| Name of the author       |

### Books Table

| Column  | Type         | Description              |
|---------|--------------|--------------------------|
| book_id | SERIAL       | Primary Key              |
| title   | VARCHAR(200) | Title of the book        |

### Author_Books Table

| Column    | Type | Description                       |
|-----------|------|-----------------------------------|
| author_id | INT  | Foreign Key referencing `authors` |
| book_id   | INT  | Foreign Key referencing `books`   |

The `author_books` table establishes a many-to-many relationship between authors and books.

## Sample Data and Relationship

### 1 author to many books
Harry Potter and the Philosopher Stone - J.K. Rowling
Harry Potter and the Chamber of Secrets - J.K. Rowling

### 1 author to 1 book
A Game of Thrones - J.R.R. Tolkien

### many authors to 1 book
Good Omens - Terry Partchett, Neil Gaiman

## API Endpoints

### Authors

- **Get All Authors**
  - `GET /api/authors`
- **Get Author by ID**
  - `GET /api/authors/{id}`
- **Create Author**
  - `POST /api/authors`
- **Update Author**
  - `PUT /api/authors/{id}`
- **Delete Author**
  - `DELETE /api/authors/{id}`

### Books

- **Get All Books**
  - `GET /api/books`
- **Get Book by ID**
  - `GET /api/books/{id}`
- **Create Book**
  - `POST /api/books`
- **Update Book**
  - `PUT /api/books/{id}`
- **Delete Book**
  - `DELETE /api/books/{id}`


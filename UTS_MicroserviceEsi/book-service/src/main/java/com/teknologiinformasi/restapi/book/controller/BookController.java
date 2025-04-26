package com.teknologiinformasi.restapi.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.teknologiinformasi.restapi.book.model.Book;
import com.teknologiinformasi.restapi.book.service.BookService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    // GET semua book
    @GetMapping
    public List<Book>getAllBooks() {
        log.info("GET /api/books accessed");
        return bookService.getAll();
    }

    // GET book berdasarkan id
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        log.info("GET /api/books/{} accessed", id);
        return bookService.getById(id)
                .map(book -> ResponseEntity.ok(book))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST membuat book baru
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        log.info("POST /api/books accessed");
        return bookService.createBook(book);
    }

    // PUT update book yang ada
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        log.info("PUT /api/books/{} accessed", id);
        try {
            Book updatedBook = bookService.updateBook(id, bookDetails);
            return ResponseEntity.ok(updatedBook);
        } catch (RuntimeException e) {
            log.error("Book with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE book
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        log.info("DELETE /api/books/{} accessed", id);
        try {
            bookService.deleteBook(id);
            return ResponseEntity.ok("Book deleted successfully");
        } catch (RuntimeException e) {
            log.error("Failed to delete book with id {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}
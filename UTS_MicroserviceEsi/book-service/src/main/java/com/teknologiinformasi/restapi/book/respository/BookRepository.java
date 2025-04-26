package com.teknologiinformasi.restapi.book.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teknologiinformasi.restapi.book.model.Book;
 

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
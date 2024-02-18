package com.example.training.services;

import com.example.training.entities.Author;
import com.example.training.entities.Book;
import com.example.training.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll(){

        return bookRepository.findAll();
    }

    public Book save(Book book, Author author){
        book.setAuthor( author );

        return bookRepository.save( book );
    }

    public Book findById( Integer id ){
        Optional<Book> optional = bookRepository.findById( id );

        return optional.isPresent() ? optional.get() : null;
    }

    public void delete( Book book ){
        bookRepository.delete( book );
    }

}

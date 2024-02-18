package com.example.training.controllers;

import com.example.training.entities.Author;
import com.example.training.entities.Book;
import com.example.training.responses.ResponseHandler;
import com.example.training.services.AuthorService;
import com.example.training.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<Object> findAll(){
        try{
            List<Book> result = bookService.findAll();

            return ResponseHandler.generateResponse("Success",HttpStatus.OK, result );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> save(@RequestBody Book book, @PathVariable Integer id ){
        try{
            Author author = authorService.findById( id );
            if( author != null ){

                Book result = bookService.save( book, author );

                return ResponseHandler.generateResponse("Succes",HttpStatus.CREATED, book );
            }
            return ResponseHandler.generateResponse("Success Author",HttpStatus.NOT_FOUND, null );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id ){
        try{
            Book book = bookService.findById( id );
            if( book != null ){

                bookService.delete( book );

                return ResponseHandler.generateResponse("Succes",HttpStatus.ACCEPTED, book );
            }

            return ResponseHandler.generateResponse("Success Author",HttpStatus.NOT_FOUND, null );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }
}

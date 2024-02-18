package com.example.training.controllers;

import com.example.training.entities.Author;
import com.example.training.entities.Book;
import com.example.training.responses.ResponseHandler;
import com.example.training.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping()
    public ResponseEntity<Object> findAll(){

        try {

            List<Author> result = authorService.findAll();

            return ResponseHandler.generateResponse("Success",HttpStatus.OK,result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById( @PathVariable Integer id ){
        try {
            Author author = authorService.findById( id );

            return  ResponseHandler.generateResponse("Success",HttpStatus.OK,author );
        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Author author ){
        try {
            Author result = authorService.save( author );

            return  ResponseHandler.generateResponse("Success",HttpStatus.CREATED,result);

        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<Object> findByCountry(@PathVariable  String country){
        try {
            List<Author> result = authorService.findByCountry( country );

            return  ResponseHandler.generateResponse("Success",HttpStatus.OK,result );
        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Object> findByName(@PathVariable  String name){
        try {
            List<Author> result = authorService.findByName( name );

            return  ResponseHandler.generateResponse("Success",HttpStatus.OK,result );
        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Object> getBooks(@PathVariable Integer id){
        try{
            Author author = authorService.findById( id );
            if( author != null ){

                List<Book> result = authorService.getBooks( author );

                return ResponseHandler.generateResponse("Succes",HttpStatus.CREATED, result );
            }

            return ResponseHandler.generateResponse("Success Author",HttpStatus.NOT_FOUND, null );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id ){
        try{
            Author author = authorService.findById( id );
            if( author != null ){

                authorService.delete( author );

                return ResponseHandler.generateResponse("Succes",HttpStatus.ACCEPTED, author );
            }

            return ResponseHandler.generateResponse("Success Author",HttpStatus.NOT_FOUND, null );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }
}

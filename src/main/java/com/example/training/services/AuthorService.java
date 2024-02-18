package com.example.training.services;

import com.example.training.entities.Author;
import com.example.training.entities.Book;
import com.example.training.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public List<Author> findAll(){

        return authorRepository.findAll();
    }

    public Author findById( Integer id ){
        Optional<Author> optional = authorRepository.findById(id);

        return optional.isPresent() ? optional.get() : null;
    }

    public Author save( Author author ){

        return authorRepository.save( author );
    }

    public List<Author> findByCountry( String country){

        return authorRepository.findByCountry( country );
    }

    public List<Author> findByName( String name ){

        return authorRepository.findByName( name );
    }

    public List<Book> getBooks( Author author ){
        return author.getBooks();
    }

    public void delete( Author author ){

        authorRepository.delete( author );
    }
}

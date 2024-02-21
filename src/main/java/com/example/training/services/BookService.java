package com.example.training.services;

import com.example.training.entities.Author;
import com.example.training.entities.Book;
import com.example.training.repositories.BookRepository;
import org.aspectj.bridge.IMessage;
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

    /*public Book updateBook(Book book, Integer id, Author author) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();

            // Actualizar los campos del libro existente con los valores del libro actualizado
            existingBook.setTitle(book.getTitle());
            existingBook.setDateRelease(book.getDateRelease());
            existingBook.setPages(book.getPages());

            // Asignar el nuevo autor al libro existente
            existingBook.setAuthor(author);

            // Guardar los cambios en la base de datos
            return bookRepository.save(existingBook);
        } else {
            // Si el libro no se encuentra, puedes lanzar una excepción o devolver null según tus necesidades
            return null;
        }
    }*/


}

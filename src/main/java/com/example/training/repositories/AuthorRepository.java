package com.example.training.repositories;

import com.example.training.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query("SELECT a FROM Author a WHERE a.name LIKE CONCAT('%',:name,'%')")
    public List<Author> findByName(String name);

    public List<Author> findByCountry(String country);
}

package com.example.quote.repository;

import com.example.quote.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    List<Quote> findByAuthor(String author);

    Optional<Quote> findByIsDailyQuoteTrue();

    @Query("SELECT q FROM Quote q ORDER BY FUNCTION('RAND')")
    List<Quote> findRandomQuote();

    @Query("SELECT DISTINCT q.author FROM Quote q ORDER BY q.author")
    List<String> findAllAuthors();
}

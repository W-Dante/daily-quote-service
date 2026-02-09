package com.example.quote.controller;

import com.example.quote.dto.QuoteRequest;
import com.example.quote.dto.QuoteResponse;
import com.example.quote.service.QuoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quotes")
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;

    /**
     * GET /api/v1/quotes - Get all quotes
     */
    @GetMapping
    public ResponseEntity<List<QuoteResponse>> getAllQuotes() {
        List<QuoteResponse> quotes = quoteService.getAllQuotes();
        return ResponseEntity.ok(quotes);
    }

    /**
     * GET /api/v1/quotes/{id} - Get a specific quote by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<QuoteResponse> getQuoteById(@PathVariable Long id) {
        QuoteResponse quote = quoteService.getQuoteById(id);
        return ResponseEntity.ok(quote);
    }

    /**
     * POST /api/v1/quotes - Create a new quote
     */
    @PostMapping
    public ResponseEntity<QuoteResponse> createQuote(@Valid @RequestBody QuoteRequest request) {
        QuoteResponse quote = quoteService.createQuote(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(quote);
    }

    /**
     * PUT /api/v1/quotes/{id} - Update an existing quote
     */
    @PutMapping("/{id}")
    public ResponseEntity<QuoteResponse> updateQuote(
            @PathVariable Long id,
            @Valid @RequestBody QuoteRequest request) {
        QuoteResponse quote = quoteService.updateQuote(id, request);
        return ResponseEntity.ok(quote);
    }

    /**
     * DELETE /api/v1/quotes/{id} - Delete a quote
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id) {
        quoteService.deleteQuote(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /api/v1/quotes/daily - Get the quote of the day
     */
    @GetMapping("/daily")
    public ResponseEntity<QuoteResponse> getDailyQuote() {
        QuoteResponse quote = quoteService.getDailyQuote();
        return ResponseEntity.ok(quote);
    }

    /**
     * GET /api/v1/quotes/random - Get a random quote
     */
    @GetMapping("/random")
    public ResponseEntity<QuoteResponse> getRandomQuote() {
        QuoteResponse quote = quoteService.getRandomQuote();
        return ResponseEntity.ok(quote);
    }

    /**
     * GET /api/v1/quotes/author/{author} - Get all quotes by a specific author
     */
    @GetMapping("/author/{author}")
    public ResponseEntity<List<QuoteResponse>> getQuotesByAuthor(@PathVariable String author) {
        List<QuoteResponse> quotes = quoteService.getQuotesByAuthor(author);
        return ResponseEntity.ok(quotes);
    }

    /**
     * GET /api/v1/quotes/authors - Get all unique authors
     */
    @GetMapping("/authors")
    public ResponseEntity<List<String>> getAllAuthors() {
        List<String> authors = quoteService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }
}

package com.example.quote.service;

import com.example.quote.dto.QuoteRequest;
import com.example.quote.dto.QuoteResponse;
import com.example.quote.exception.QuoteNotFoundException;
import com.example.quote.model.Quote;
import com.example.quote.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuoteService {

    private final QuoteRepository quoteRepository;

    @Transactional(readOnly = true)
    public List<QuoteResponse> getAllQuotes() {
        log.info("Fetching all quotes");
        return quoteRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public QuoteResponse getQuoteById(Long id) {
        log.info("Fetching quote with id: {}", id);
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(() -> new QuoteNotFoundException(id));
        return convertToResponse(quote);
    }

    @Transactional
    public QuoteResponse createQuote(QuoteRequest request) {
        log.info("Creating new quote by author: {}", request.getAuthor());
        Quote quote = Quote.builder()
                .text(request.getText())
                .author(request.getAuthor())
                .isDailyQuote(false)
                .build();
        
        Quote savedQuote = quoteRepository.save(quote);
        log.info("Quote created with id: {}", savedQuote.getId());
        return convertToResponse(savedQuote);
    }

    @Transactional
    public QuoteResponse updateQuote(Long id, QuoteRequest request) {
        log.info("Updating quote with id: {}", id);
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(() -> new QuoteNotFoundException(id));
        
        quote.setText(request.getText());
        quote.setAuthor(request.getAuthor());
        
        Quote updatedQuote = quoteRepository.save(quote);
        log.info("Quote updated successfully");
        return convertToResponse(updatedQuote);
    }

    @Transactional
    public void deleteQuote(Long id) {
        log.info("Deleting quote with id: {}", id);
        if (!quoteRepository.existsById(id)) {
            throw new QuoteNotFoundException(id);
        }
        quoteRepository.deleteById(id);
        log.info("Quote deleted successfully");
    }

    @Transactional(readOnly = true)
    public QuoteResponse getDailyQuote() {
        log.info("Fetching daily quote");
        Quote dailyQuote = quoteRepository.findByIsDailyQuoteTrue()
                .orElseThrow(() -> new QuoteNotFoundException("No daily quote available"));
        return convertToResponse(dailyQuote);
    }

    @Transactional(readOnly = true)
    public QuoteResponse getRandomQuote() {
        log.info("Fetching random quote");
        List<Quote> quotes = quoteRepository.findRandomQuote();
        if (quotes.isEmpty()) {
            throw new QuoteNotFoundException("No quotes available");
        }
        return convertToResponse(quotes.get(0));
    }

    @Transactional(readOnly = true)
    public List<QuoteResponse> getQuotesByAuthor(String author) {
        log.info("Fetching quotes by author: {}", author);
        return quoteRepository.findByAuthor(author)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<String> getAllAuthors() {
        log.info("Fetching all authors");
        return quoteRepository.findAllAuthors();
    }

    /**
     * Scheduled task that runs daily at midnight to select a new daily quote
     */
    @Scheduled(cron = "0 0 0 * * *") // Runs at midnight every day
    @Transactional
    public void updateDailyQuote() {
        log.info("Running scheduled task: updateDailyQuote");
        
        // Clear previous daily quote
        quoteRepository.findByIsDailyQuoteTrue().ifPresent(quote -> {
            quote.setDailyQuote(false);
            quoteRepository.save(quote);
        });
        
        // Select a random quote as the new daily quote
        List<Quote> quotes = quoteRepository.findRandomQuote();
        if (!quotes.isEmpty()) {
            Quote newDailyQuote = quotes.get(0);
            newDailyQuote.setDailyQuote(true);
            quoteRepository.save(newDailyQuote);
            log.info("New daily quote set: '{}' by {}", 
                    newDailyQuote.getText(), newDailyQuote.getAuthor());
        } else {
            log.warn("No quotes available to set as daily quote");
        }
    }

    private QuoteResponse convertToResponse(Quote quote) {
        return QuoteResponse.builder()
                .id(quote.getId())
                .text(quote.getText())
                .author(quote.getAuthor())
                .createdAt(quote.getCreatedAt())
                .isDailyQuote(quote.isDailyQuote())
                .build();
    }
}

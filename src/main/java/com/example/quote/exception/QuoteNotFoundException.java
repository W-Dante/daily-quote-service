package com.example.quote.exception;

public class QuoteNotFoundException extends RuntimeException {
    
    public QuoteNotFoundException(String message) {
        super(message);
    }
    
    public QuoteNotFoundException(Long id) {
        super("Quote not found with id: " + id);
    }
}

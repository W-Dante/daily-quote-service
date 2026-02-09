package com.example.quote.config;

import com.example.quote.model.Quote;
import com.example.quote.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final QuoteRepository quoteRepository;

    @Override
    public void run(String... args) {
        if (quoteRepository.count() == 0) {
            log.info("Initializing database with sample quotes...");
            
            List<Quote> quotes = Arrays.asList(
                Quote.builder()
                    .text("The only way to do great work is to love what you do.")
                    .author("Steve Jobs")
                    .isDailyQuote(true)
                    .build(),
                Quote.builder()
                    .text("Innovation distinguishes between a leader and a follower.")
                    .author("Steve Jobs")
                    .build(),
                Quote.builder()
                    .text("Life is what happens when you're busy making other plans.")
                    .author("John Lennon")
                    .build(),
                Quote.builder()
                    .text("The future belongs to those who believe in the beauty of their dreams.")
                    .author("Eleanor Roosevelt")
                    .build(),
                Quote.builder()
                    .text("It is during our darkest moments that we must focus to see the light.")
                    .author("Aristotle")
                    .build(),
                Quote.builder()
                    .text("The only impossible journey is the one you never begin.")
                    .author("Tony Robbins")
                    .build(),
                Quote.builder()
                    .text("In the middle of difficulty lies opportunity.")
                    .author("Albert Einstein")
                    .build(),
                Quote.builder()
                    .text("The best time to plant a tree was 20 years ago. The second best time is now.")
                    .author("Chinese Proverb")
                    .build(),
                Quote.builder()
                    .text("Your time is limited, don't waste it living someone else's life.")
                    .author("Steve Jobs")
                    .build(),
                Quote.builder()
                    .text("Success is not final, failure is not fatal: it is the courage to continue that counts.")
                    .author("Winston Churchill")
                    .build()
            );
            
            quoteRepository.saveAll(quotes);
            log.info("Database initialized with {} quotes", quotes.size());
        } else {
            log.info("Database already contains data, skipping initialization");
        }
    }
}

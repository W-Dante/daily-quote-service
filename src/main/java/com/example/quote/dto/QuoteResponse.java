package com.example.quote.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuoteResponse {

    private Long id;
    private String text;
    private String author;
    private LocalDateTime createdAt;
    private boolean isDailyQuote;
}

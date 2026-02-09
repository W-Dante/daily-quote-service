package com.example.quote.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuoteRequest {

    @NotBlank(message = "Quote text cannot be empty")
    @Size(min = 10, max = 500, message = "Quote must be between 10 and 500 characters")
    private String text;

    @NotBlank(message = "Author cannot be empty")
    @Size(max = 100, message = "Author name cannot exceed 100 characters")
    private String author;
}

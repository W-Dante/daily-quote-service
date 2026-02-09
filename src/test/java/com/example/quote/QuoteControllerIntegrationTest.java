package com.example.quote;

import com.example.quote.dto.QuoteRequest;
import com.example.quote.repository.QuoteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class QuoteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // Tests will use the initialized data from DataInitializer
    }

    @Test
    @WithMockUser
    void shouldGetAllQuotes() throws Exception {
        mockMvc.perform(get("/api/v1/quotes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @WithMockUser
    void shouldGetQuoteById() throws Exception {
        mockMvc.perform(get("/api/v1/quotes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @WithMockUser
    void shouldCreateQuote() throws Exception {
        QuoteRequest request = QuoteRequest.builder()
                .text("This is a test quote for our application.")
                .author("Test Author")
                .build();

        mockMvc.perform(post("/api/v1/quotes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.text").value(request.getText()))
                .andExpect(jsonPath("$.author").value(request.getAuthor()));
    }

    @Test
    @WithMockUser
    void shouldGetDailyQuote() throws Exception {
        mockMvc.perform(get("/api/v1/quotes/daily"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isDailyQuote").value(true));
    }

    @Test
    @WithMockUser
    void shouldGetRandomQuote() throws Exception {
        mockMvc.perform(get("/api/v1/quotes/random"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @WithMockUser
    void shouldReturnNotFoundForInvalidId() throws Exception {
        mockMvc.perform(get("/api/v1/quotes/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void shouldValidateQuoteRequest() throws Exception {
        QuoteRequest request = QuoteRequest.builder()
                .text("Short")  // Too short (min 10 chars)
                .author("")     // Empty author
                .build();

        mockMvc.perform(post("/api/v1/quotes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}

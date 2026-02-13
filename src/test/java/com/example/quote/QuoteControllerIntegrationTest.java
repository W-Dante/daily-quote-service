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
import org.springframework.security.test.context.support.WithMockUser;
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
        QuoteRequest request = new QuoteRequest();
        request.setText("This is a test quote for our application.");
        request.setAuthor("Test Author");

        mockMvc.perform(post("/api/v1/quotes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.text").value("This is a test quote for our application."))
                .andExpect(jsonPath("$.author").value("Test Author"));
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
        QuoteRequest request = new QuoteRequest();
        request.setText("Short");  // Too short (min 10 chars)
        request.setAuthor("");     // Empty author

        mockMvc.perform(post("/api/v1/quotes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    @WithMockUser
    void shouldUpdateQuote() throws Exception {
        QuoteRequest request = new QuoteRequest();
        request.setText("Updated quote text that is long enough to pass validation.");
        request.setAuthor("Updated Author");

        mockMvc.perform(put("/api/v1/quotes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("Updated quote text that is long enough to pass validation."))
                .andExpect(jsonPath("$.author").value("Updated Author"));
    }
    
    @Test
    @WithMockUser
    void shouldDeleteQuote() throws Exception {
        mockMvc.perform(delete("/api/v1/quotes/2"))
                .andExpect(status().isNoContent());
    }
    
    @Test
    @WithMockUser
    void shouldGetQuotesByAuthor() throws Exception {
        mockMvc.perform(get("/api/v1/quotes/author/Steve Jobs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
    
    @Test
    @WithMockUser
    void shouldGetAllAuthors() throws Exception {
        mockMvc.perform(get("/api/v1/quotes/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}

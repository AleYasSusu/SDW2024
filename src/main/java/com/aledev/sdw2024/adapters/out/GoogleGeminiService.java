package com.aledev.sdw2024.adapters.out;

import com.aledev.sdw2024.domain.ports.GenerativeAiService;
import feign.FeignException;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@ConditionalOnProperty(name = "generative-ai.provider", havingValue = "GEMINI", matchIfMissing = true)
@FeignClient(name = "geminiApi", url = "${gemini.base-url}", configuration = GoogleGeminiService.Config.class)
public interface GoogleGeminiService extends GenerativeAiService {

    @PostMapping("/v1beta/models/gemini-pro:generateContent")
    GoogleGeminiResponse textOnlyInput(GoogleGeminiRequest req);

    @Override
    default String generateContent(String objective, String context) {

        String prompt = """
                %s
                %s
                """.formatted(objective, context);

        GoogleGeminiRequest geminiRequest = new GoogleGeminiRequest(
                List.of(new Content(List.of(new Part(prompt))))
        );
        try {
            GoogleGeminiResponse geminiResponse = textOnlyInput(geminiRequest);
            return geminiResponse.candidates().getFirst().content().parts().getFirst().text();
        } catch (FeignException httpErrors) {
            return "Deu ruim!! Erro de comunicação com a API do Google Gemini";
        } catch (Exception unexpedtedError) {
            return "Deu mais Ruim ainda! O retorno da API do Goofle Gemini não contḿ os dados esperados.";
        }

    }

    record GoogleGeminiRequest(List<Content> contents) { }

    record GoogleGeminiResponse(List<Candidate> candidates) { }

    record Candidate(Content content) { }

    record Content(List<Part> parts) { }

    record Part(String text) { }

    class Config {
        @Bean
        public RequestInterceptor apiKeyRequestInterceptor(@Value("${gemini.api-key}") String apiKey) {
            return requestTemplate ->
                    requestTemplate.query("key", apiKey);
        }
    }
}

package com.aledev.sdw2024.adapters.in;

import com.aledev.sdw2024.application.AskChampionsUseCase;
import com.aledev.sdw2024.application.ListChampionsUseCase;
import com.aledev.sdw2024.domain.model.Champion;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Questions for Champions", description = "endpoints question for champions")
@RestController
@RequestMapping("/questions")
public record AskChampionsRestController(AskChampionsUseCase useCase) {

    @CrossOrigin
    @PostMapping("/{championId}")
    public AskChampionResponse askChampion(@PathVariable Long championId,
                                           @RequestBody AskChampionRequest request) {
        String answer = useCase.askChampion(championId, request.question());

        return new AskChampionResponse(answer);
    }

    public record AskChampionRequest(String question) {
    }

    public record AskChampionResponse(String answer) {
    }
}

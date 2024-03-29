package com.aledev.sdw2024.adapters.in;

import com.aledev.sdw2024.application.ListChampionsUseCase;
import com.aledev.sdw2024.domain.model.Champion;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Champions", description = "LOL Champion Domain Endpoints")
@RestController
@RequestMapping("/champions")
public record ListChampionsRestController(ListChampionsUseCase useCase) {
    @CrossOrigin
    @GetMapping
    public List<Champion> findAllChampion() {
        return useCase.findAll();
    }
}

package com.aledev.sdw2024.application;

import com.aledev.sdw2024.domain.model.Champion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ListChampionsUseCaseIntegrationTest {
    @Autowired
    private ListChampionsUseCase listChampionsUseCase;

    @Test
    void testListChampions() {
        List<Champion> champions = listChampionsUseCase.findAll();

        assertEquals(12, champions.size());
    }
}

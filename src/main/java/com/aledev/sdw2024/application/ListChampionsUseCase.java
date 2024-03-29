package com.aledev.sdw2024.application;

import com.aledev.sdw2024.domain.model.Champion;
import com.aledev.sdw2024.domain.ports.ChampionsRepository;

import java.util.List;

public record ListChampionsUseCase(ChampionsRepository championsRepository) {

    public List<Champion> findAll(){
        return championsRepository.findAll();
    }
}

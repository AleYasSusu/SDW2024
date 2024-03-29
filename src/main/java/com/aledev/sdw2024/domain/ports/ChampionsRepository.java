package com.aledev.sdw2024.domain.ports;

import com.aledev.sdw2024.domain.model.Champion;

import java.util.List;
import java.util.Optional;

public interface ChampionsRepository {

    List<Champion> findAll();

    Optional<Champion> findById(Long id);
}

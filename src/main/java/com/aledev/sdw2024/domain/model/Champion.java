package com.aledev.sdw2024.domain.model;

import org.springframework.lang.Nullable;

public record Champion(Long id,
                       String name,
                       String role,
                       String lore,
                       String imageUrl) {
    public String genereteContextByQuestion(String question){
        return """
                Pergunta : %s
                Nome do Campeão %s
                Função: %s
                Lore (História)
                """.formatted(question, this.name, this.role, this.lore);

    }

}
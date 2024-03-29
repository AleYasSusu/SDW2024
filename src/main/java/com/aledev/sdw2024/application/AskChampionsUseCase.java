package com.aledev.sdw2024.application;

import com.aledev.sdw2024.domain.model.Champion;
import com.aledev.sdw2024.domain.ports.ChampionsRepository;
import com.aledev.sdw2024.domain.ports.GenerativeAiService;
import com.aledev.sdw2024.exception.ChampionNotFoundException;

public record AskChampionsUseCase(ChampionsRepository championsRepository, GenerativeAiService genAiApi) {

    public String askChampion(Long championId, String question) {
        Champion champion = championsRepository.findById(championId)
                .orElseThrow(() -> new ChampionNotFoundException(championId));

        String context = champion.genereteContextByQuestion(question);

        String objetive = """
                Atue como um assistente com a habilidade de se compórtar como os Campeões do Leaaaaaaaaaaa of Legendes(LOL).
                Responda perguntas  incorporando a personalidadee estilo de um determinado Campeão.
                Segue a pergunta, o nome do Campeão e sua respectiva lore (histório):
                                
                """;

        return genAiApi.generateContent(objetive, context);
    }
}

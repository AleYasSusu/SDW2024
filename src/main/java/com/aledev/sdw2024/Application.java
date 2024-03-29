package com.aledev.sdw2024;

import com.aledev.sdw2024.application.AskChampionsUseCase;
import com.aledev.sdw2024.application.ListChampionsUseCase;
import com.aledev.sdw2024.domain.ports.ChampionsRepository;
import com.aledev.sdw2024.domain.ports.GenerativeAiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableFeignClients
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ListChampionsUseCase providerListChampionsUseCase(ChampionsRepository championsRepository){
        return new ListChampionsUseCase(championsRepository);
    }
    @Bean
    public AskChampionsUseCase providerAskChampionsUseCase(ChampionsRepository championsRepository,
                                                           GenerativeAiService genAiService ){
        return new AskChampionsUseCase(championsRepository, genAiService);
    }
}

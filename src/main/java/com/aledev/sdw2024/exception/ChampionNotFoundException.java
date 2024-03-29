package com.aledev.sdw2024.exception;

public class ChampionNotFoundException extends RuntimeException{
    public ChampionNotFoundException(Long championId){
        super("Champion  %d not found".formatted(championId));
    }
}

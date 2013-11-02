package com.regawmod.hardestgame;

public class GameStats
{
    private int currentLevel;
    private int amountOfDeaths;

    public GameStats()
    {
        this.currentLevel = 1;
        this.amountOfDeaths = 0;
    }

    public void incrementDeaths()
    {
        this.amountOfDeaths++;
    }

    public void incrementLevel()
    {
        this.currentLevel++;
    }

    public int getCurrentLevel()
    {
        return this.currentLevel;
    }

    public int getAmountOfDeaths()
    {
        return this.amountOfDeaths;
    }
}

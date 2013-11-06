package com.regawmod.hardestgame;

public class GameData
{
    private int currentLevel;
    private int amountOfDeaths;

    public GameData()
    {
        this.currentLevel = 0;
        this.amountOfDeaths = 0;
    }

    public void incrementDeaths()
    {
        this.amountOfDeaths++;
    }

    public void setCurrentLevel(int level)
    {
        this.currentLevel = level;
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

package com.regawmod.hardestgame;

import java.util.ArrayList;
import java.util.List;
import com.regawmod.hardestgame.level.BrandonLevel;
import com.regawmod.hardestgame.level.DanLevel;
import com.regawmod.hardestgame.level.Level;

/**
 * Holds data about the game.
 * 
 * @author Dan Wager
 */
public class GameData
{
    /** The list of all level classes in the game */
    private List<Class<? extends Level>> levels;

    /** Our current level index */
    private int currentLevelindex;
    /** The amount of player deaths */
    private int amountOfDeaths;

    /**
     * Creates a new {@link GameData} for use in game.
     */
    public GameData()
    {
        initLevels();

        this.currentLevelindex = 0;
        this.amountOfDeaths = 0;
    }

    /**
     * Initializes all of the levels in the game.
     * Add your levels to the list here.
     */
    private void initLevels()
    {
        this.levels = new ArrayList<Class<? extends Level>>();

        this.levels.add(DanLevel.class);
        this.levels.add(BrandonLevel.class);
    }

    /**
     * Gets a new instance of the level at the current level index.
     * 
     * @return A new current level instance, or null
     */
    public Level getNewLevel()
    {
        try
        {
            return this.levels.get(this.currentLevelindex).newInstance();
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Increases the current level index by one.
     */
    public void incrementDeaths()
    {
        this.amountOfDeaths++;
    }

    /**
     * Sets the current level.
     * 
     * @param level The level to set as current (0-based)
     */
    public void setCurrentLevel(int level)
    {
        this.currentLevelindex = level;
    }

    /**
     * Increments the current level by one.
     */
    public void incrementLevel()
    {
        this.currentLevelindex++;
    }

    /**
     * Gets a value indicating the index of the current level.
     * 
     * @return The current level's index
     */
    public int getCurrentLevel()
    {
        return this.currentLevelindex;
    }

    /**
     * Gets a value indicating the current amount of player deaths.
     * 
     * @return The current amount of player deaths
     */
    public int getAmountOfDeaths()
    {
        return this.amountOfDeaths;
    }
}

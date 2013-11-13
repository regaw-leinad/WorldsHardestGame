package com.regawmod.hardestgame;

import java.util.List;
import com.regawmod.hardestgame.level.Level;

/**
 * Holds data about the game.
 * 
 * @author Dan Wager
 */
public class GameData
{
    /** The level loader */
    private LevelLoader levelLoader;
    /** The list of all level classes in the game */
    private List<Class<? extends Level>> levels;

    /** Our current level index */
    private int currentLevelIndex;
    /** The amount of player deaths */
    private int amountOfDeaths;

    /**
     * Creates a new {@link GameData} for use in game.
     */
    public GameData()
    {
        initLevels();

        this.currentLevelIndex = 0;
        this.amountOfDeaths = 0;
    }

    /**
     * Initializes all of the levels in the game.
     * Add your levels to the list here.
     */
    private void initLevels()
    {
        this.levelLoader = new LevelLoader();

        this.levels = this.levelLoader.getLevelCollection();
    }

    /**
     * Gets a list of all loaded level classes
     * 
     * @return A list of all loaded level classes
     */
    public List<Class<? extends Level>> getLevels()
    {
        return this.levels;
    }

    /**
     * Gets a value indicating how many levels are loaded
     * 
     * @return The number of levels that are loaded
     */
    public int getLevelCount()
    {
        return this.levels.size();
    }

    /**
     * Gets a new instance of the level at the current level index.
     * 
     * @return A new current level instance, or null
     */
    public Level getLevelInstance()
    {
        try
        {
            return this.levels.get(this.currentLevelIndex).newInstance();
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
     * Increments the current level by one.
     */
    public void incrementLevel()
    {
        this.currentLevelIndex = getValidIndex(this.currentLevelIndex + 1);
    }

    /**
     * Decrements the current level by one.
     */
    public void decrementLevel()
    {
        this.currentLevelIndex = getValidIndex(this.currentLevelIndex - 1);
    }

    /**
     * Gets a value indicating the index of the current level.
     * 
     * @return The current level's index
     */
    public int getCurrentLevel()
    {
        return this.currentLevelIndex;
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

    /**
     * Returns a valid index within range
     * 
     * @param index The index to validate
     * @return The valid index
     */
    private int getValidIndex(int index)
    {
        if (index < 0)
            return this.levels.size() - 1;

        if (this.levels.size() > 0)
            return index % this.levels.size();
        else
            return 0;
    }
}

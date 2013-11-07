package com.regawmod.hardestgame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import com.regawmod.hardestgame.level.Level;
import com.regawmod.hardestgame.state.GameState;
import com.regawmod.hardestgame.state.InGameState;
import com.regawmod.hardestgame.state.LevelCompleteGameState;
import com.regawmod.hardestgame.state.MainMenuState;

/**
 * The main game class.
 * 
 * @author Dan Wager
 */
public class GameMain extends StateBasedGame
{
    /** The game's data */
    private GameData gameData;

    public GameMain(String name)
    {
        super(name);
        this.gameData = new GameData();
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException
    {
        this.addState(new MainMenuState(this));
        this.addState(new InGameState(this));
        this.addState(new LevelCompleteGameState(this));

        this.enterState(GameState.IN_GAME);
    }

    /**
     * Gets a new instance of the current level.
     * 
     * @return A new level instance
     */
    public Level getNewCurrentLevel()
    {
        return this.gameData.getNewLevel();
    }

    /**
     * Increments the player deaths by one.
     */
    public void incrementDeaths()
    {
        this.gameData.incrementDeaths();
    }

    /**
     * Sets the current level to the specified level.
     * 
     * @param level The level number to set
     */
    public void setCurrentLevel(int level)
    {
        this.gameData.setCurrentLevel(level);
    }

    /**
     * Increments the level by one.
     */
    public void incrementLevel()
    {
        this.gameData.incrementLevel();
    }

    /**
     * Gets a value indicating the current level.
     * 
     * @return The current level's index
     */
    public int getCurrentLevel()
    {
        return this.gameData.getCurrentLevel();
    }

    /**
     * Gets a value indicating the current amount of player deaths.
     * 
     * @return The current amount of player deaths
     */
    public int getAmountOfDeaths()
    {
        return this.gameData.getAmountOfDeaths();
    }
}

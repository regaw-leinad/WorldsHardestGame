package com.regawmod.hardestgame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
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
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException
    {
        this.gameData = new GameData();

        this.addState(new MainMenuState(this));
        this.addState(new InGameState(this));
        this.addState(new LevelCompleteGameState(this));
    }

    /**
     * Get's the game's data
     * 
     * @return The game's data
     */
    public GameData getGameData()
    {
        return this.gameData;
    }
}

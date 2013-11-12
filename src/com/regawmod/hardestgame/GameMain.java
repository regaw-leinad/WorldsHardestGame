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
        this.gameData = new GameData();
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException
    {
        this.addState(new MainMenuState(this));
        this.addState(new InGameState(this));
        this.addState(new LevelCompleteGameState(this));

        //this.enterState(GameState.IN_GAME);
    }

    public GameData getGameData()
    {
        return this.gameData;
    }
}

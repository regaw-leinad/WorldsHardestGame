package com.regawmod.hardestgame;

import java.util.List;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import com.regawmod.hardestgame.level.Level;
import com.regawmod.hardestgame.state.GameState;
import com.regawmod.hardestgame.state.InGameState;
import com.regawmod.hardestgame.state.LevelCompleteGameState;
import com.regawmod.hardestgame.state.MainMenuState;

public class GameMain extends StateBasedGame
{
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

    public List<Class<? extends Level>> getLevelCollection()
    {
        return LevelLoader.getLevelCollection();
    }

    public Level getNewCurrentLevel()
    {
        try
        {
            return LevelLoader.getLevel(this.gameData.getCurrentLevel());
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    public void incrementDeaths()
    {
        this.gameData.incrementDeaths();
    }

    public void setCurrentLevel(int level)
    {
        this.gameData.setCurrentLevel(level);
    }

    public int getCurrentLevel()
    {
        return this.gameData.getCurrentLevel();
    }

    public int getAmountOfDeaths()
    {
        return this.gameData.getAmountOfDeaths();
    }
}

package com.regawmod.hardestgame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import com.regawmod.hardestgame.state.InGameState;
import com.regawmod.hardestgame.state.LevelCompleteGameState;

public class GameMain extends StateBasedGame
{
    public GameMain(String name)
    {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException
    {
        this.addState(new InGameState());
        this.addState(new LevelCompleteGameState());
    }
}

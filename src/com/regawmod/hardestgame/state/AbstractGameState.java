package com.regawmod.hardestgame.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class AbstractGameState extends BasicGameState
{
    @Override
    public final void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
    {
        update(container, game, delta / 1000.0f);
    }

    public abstract void update(GameContainer container, StateBasedGame game, float delta) throws SlickException;
}

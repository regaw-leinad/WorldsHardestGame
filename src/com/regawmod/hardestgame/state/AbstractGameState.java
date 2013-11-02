package com.regawmod.hardestgame.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class AbstractGameState extends BasicGameState
{
    @Override
    public final void update(GameContainer gc, StateBasedGame game, int dt) throws SlickException
    {
        update(gc, game, dt / 1000.0f);
    }

    public abstract void update(GameContainer gc, StateBasedGame game, float dt) throws SlickException;
}

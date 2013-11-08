package com.regawmod.hardestgame.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * A GameState that uses delta time in seconds, not milliseconds.
 * 
 * @author Dan Wager
 */
public abstract class AbstractGameState extends BasicGameState
{
    @Override
    public final void update(GameContainer gc, StateBasedGame game, int dt) throws SlickException
    {
        update(gc, game, dt / 1000.0f);
    }

    /**
     * Update the state's logic based on the amount of time that has passed.
     * 
     * @param gc The game's container
     * @param game The game that holds this state
     * @param dt The amount of time that has passed in seconds since the last update
     * @throws SlickException
     */
    public abstract void update(GameContainer gc, StateBasedGame game, float dt) throws SlickException;
}

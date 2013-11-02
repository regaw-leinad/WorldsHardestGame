package com.regawmod.hardestgame.state;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class LevelCompleteGameState extends AbstractGameState
{
    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException
    {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException
    {
        g.setBackground(Color.black);
        g.setColor(Color.white);

        g.drawString("You won that level!", 300, 250);
        g.drawString("Press SPACE to play the next level!", 230, 270);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, float dt) throws SlickException
    {
        if (gc.getInput().isKeyPressed(Input.KEY_SPACE))
            game.enterState(GameState.IN_GAME, new FadeOutTransition(), new FadeInTransition());
    }

    @Override
    public int getID()
    {
        return GameState.LEVEL_COMPLETE;
    }
}

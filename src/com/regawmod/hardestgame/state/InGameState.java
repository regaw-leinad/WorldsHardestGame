package com.regawmod.hardestgame.state;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import com.regawmod.hardestgame.level.Level;
import com.regawmod.hardestgame.level.TestLevel;

public class InGameState extends AbstractGameState
{
    private Level level;

    private int deathCount;
    private int levelNumber;
    private boolean createNextLevel;

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException
    {
        this.level = new TestLevel();
        this.deathCount = 0;
        this.levelNumber = 1;
        this.createNextLevel = false;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, float dt) throws SlickException
    {
        level.update(gc, dt);

        if (this.level.isLevelComplete())
        {
            this.createNextLevel = true;
            game.enterState(GameState.LEVEL_COMPLETE, new FadeOutTransition(), new FadeInTransition());
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException
    {
        level.render(g);

        g.setColor(Color.black);
        g.fillRect(0, 0, gc.getWidth(), 60);
    }

    @Override
    public void leave(GameContainer gc, StateBasedGame game) throws SlickException
    {
        if (this.createNextLevel)
        {
            this.levelNumber++;
            this.level = new TestLevel();
        }

        super.leave(gc, game);
    }

    @Override
    public int getID()
    {
        return GameState.IN_GAME;
    }
}

package com.regawmod.hardestgame.state;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import com.regawmod.hardestgame.GameMain;
import com.regawmod.hardestgame.level.Level;

public class InGameState extends AbstractGameState
{
    private Level level;
    private GameMain gameMain;

    private boolean createNextLevel;

    public InGameState(GameMain gameMain)
    {
        this.gameMain = gameMain;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException
    {
        this.level = this.gameMain.getNewCurrentLevel();
        this.createNextLevel = false;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, float dt) throws SlickException
    {
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
            gc.exit();

        level.update(gc, dt);

        if (this.level.hasPlayerDied())
        {
            this.gameMain.incrementDeaths();
        }
        else if (this.level.isLevelComplete())
        {
            this.createNextLevel = true;
            game.enterState(GameState.LEVEL_COMPLETE, new FadeOutTransition(), new FadeInTransition());
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException
    {
        g.setColor(Color.black);
        g.fillRect(0, 0, gc.getWidth(), 60);

        g.setColor(Color.white);
        g.drawString("Deaths: " + this.gameMain.getAmountOfDeaths(), 280, 10);

        level.render(g);
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame game) throws SlickException
    {
        if (this.createNextLevel)
            this.level = this.gameMain.getNewCurrentLevel();

        super.leave(gc, game);
    }

    @Override
    public int getID()
    {
        return GameState.IN_GAME;
    }
}

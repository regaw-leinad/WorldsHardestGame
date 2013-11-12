package com.regawmod.hardestgame.state;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import com.regawmod.hardestgame.GameData;
import com.regawmod.hardestgame.GameMain;
import com.regawmod.hardestgame.level.Level;

/**
 * The state we're in while playing a level.
 * 
 * @author Dan Wager
 */
public class InGameState extends AbstractGameState
{
    /** The current level we're playing */
    private Level level;
    /** The main game instance */
    private GameMain gameMain;

    /** If we should create a new level on return */
    private boolean createNextLevel;

    /**
     * Creates a new {@link InGameState}.
     * 
     * @param gameMain The main game instance
     */
    public InGameState(GameMain gameMain)
    {
        this.gameMain = gameMain;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException
    {
        //this.level = getGameData().getLevelInstance();
        this.createNextLevel = true;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, float dt) throws SlickException
    {
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
            gc.exit();

        level.update(gc, dt);

        if (this.level.hasPlayerDied())
        {
            getGameData().incrementDeaths();
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
        renderStats(gc, g);
        level.render(g);
    }

    /**
     * Renders the statistics for the current game.
     * 
     * @param gc The game container
     * @param g The graphics object
     */
    private void renderStats(GameContainer gc, Graphics g)
    {
        g.setColor(Color.black);
        g.fillRect(0, 0, gc.getWidth(), 60);

        g.setColor(Color.white);
        g.drawString("Deaths: " + getGameData().getAmountOfDeaths(), 280, 10);
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame game) throws SlickException
    {
        if (this.createNextLevel)
        {
            this.level = getGameData().getLevelInstance();
            this.createNextLevel = false;
        }
    }

    @Override
    public int getID()
    {
        return GameState.IN_GAME;
    }

    private GameData getGameData()
    {
        return this.gameMain.getGameData();
    }
}

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

/**
 * The state we're in after completing a level.
 * 
 * @author Dan Wager
 */
public final class LevelCompleteGameState extends AbstractGameState
{
    /** The main game instance */
    private GameMain gameMain;

    /**
     * Creates a new {@link LevelCompleteGameState}.
     * 
     * @param gameMain The main game instance
     */
    public LevelCompleteGameState(GameMain gameMain)
    {
        this.gameMain = gameMain;
    }

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
        g.drawString("Press SPACE to return to the main menu!", 230, 270);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, float dt) throws SlickException
    {
        if (gc.getInput().isKeyPressed(Input.KEY_SPACE))
            game.enterState(GameState.MAIN_MENU, new FadeOutTransition(), new FadeInTransition());
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame game) throws SlickException
    {
        getGameData().incrementLevel();
    }

    @Override
    public void leave(GameContainer gc, StateBasedGame game) throws SlickException
    {
        gc.getInput().clearKeyPressedRecord();
    }

    @Override
    public int getID()
    {
        return GameState.LEVEL_COMPLETE;
    }

    /**
     * Gets the game data
     * 
     * @return The game data
     */
    private GameData getGameData()
    {
        return this.gameMain.getGameData();
    }
}

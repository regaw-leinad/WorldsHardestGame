package com.regawmod.hardestgame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.Transition;
import com.regawmod.hardestgame.state.InGameState;
import com.regawmod.hardestgame.state.LevelCompleteGameState;
import com.regawmod.hardestgame.state.MainMenuState;

/**
 * The main game class.
 * 
 * @author Dan Wager
 */
public class GameMain extends StateBasedGame
{
    /** The game's data */
    private GameData gameData;

    /** The default fade out transition */
    private Transition fadeOutTransition;
    /** The default fade in transition */
    private Transition fadeInTransition;

    public GameMain(String name)
    {
        super(name);

        this.fadeOutTransition = new FadeOutTransition();
        this.fadeInTransition = new FadeInTransition();
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException
    {
        this.gameData = new GameData();

        this.addState(new MainMenuState(this));
        this.addState(new InGameState(this));
        this.addState(new LevelCompleteGameState(this));
    }

    /**
     * Get's the game's data
     * 
     * @return The game's data
     */
    public GameData getGameData()
    {
        return this.gameData;
    }

    /**
     * Gets a default {@link FadeOutTransition}
     * 
     * @return The default fade out transition
     */
    public Transition getFadeOutTransition()
    {
        return this.fadeOutTransition;
    }

    /**
     * Gets a default {@link FadeInTransition}
     * 
     * @return The default fade in transition
     */
    public Transition getFadeInTransition()
    {
        return this.fadeInTransition;
    }
}

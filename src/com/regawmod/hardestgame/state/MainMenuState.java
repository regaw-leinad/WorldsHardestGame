package com.regawmod.hardestgame.state;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import com.regawmod.hardestgame.GameData;
import com.regawmod.hardestgame.GameMain;
import com.regawmod.hardestgame.ResourceManager;
import com.regawmod.slick.ui.Button;

/**
 * The main menu state.
 * 
 * @author Dan Wager
 */
public final class MainMenuState extends AbstractGameState
{
    /** The main game instance */
    private GameMain gameMain;

    private Font title = new Font("Consolas", Font.BOLD, 48);
    private TrueTypeFont titleFont = new TrueTypeFont(title, true);
    private Button btnPlay;
    private Button btnLeft;
    private Button btnRight;
    private Image levelThumbnail;
    private boolean hasLoadedLevels;
    private Color backgroundColor;

    /**
     * Creates a new {@link MainMenuState}.
     * 
     * @param game The main game instance
     */
    public MainMenuState(GameMain game)
    {
        this.gameMain = game;
        this.backgroundColor = new Color(0, 127, 127);
    }

    @Override
    public void init(GameContainer gc, final StateBasedGame game) throws SlickException
    {
        this.hasLoadedLevels = getGameData().getLevelCount() > 0;

        this.btnPlay = new Button(gc, ResourceManager.getImage("btn_play"), 0, 485);
        this.btnPlay.setCenterX(400);
        this.btnPlay.setMouseOverImage(ResourceManager.getImage("btn_play_h"));
        this.btnPlay.setMouseDownImage(ResourceManager.getImage("btn_play_p"));
        this.btnPlay.addListener(new ComponentListener()
        {
            @Override
            public void componentActivated(AbstractComponent source)
            {
                if (hasLoadedLevels)
                    game.enterState(GameState.IN_GAME, new FadeOutTransition(), new FadeInTransition());
            }
        });

        this.btnRight = new Button(gc, ResourceManager.getImage("btn_arrow"), 600, 250);
        this.btnRight.setCenterX(700);
        this.btnRight.setMouseOverImage(ResourceManager.getImage("btn_arrow_h"));
        this.btnRight.setMouseDownImage(ResourceManager.getImage("btn_arrow_p"));
        this.btnRight.addListener(new ComponentListener()
        {
            @Override
            public void componentActivated(AbstractComponent source)
            {
                getGameData().incrementLevel();
                updateThumbnail();
            }
        });

        this.btnLeft = new Button(gc, ResourceManager.getImage("btn_arrow").getFlippedCopy(true, false), 120, 250);
        this.btnLeft.setCenterX(100);
        this.btnLeft.setMouseOverImage(ResourceManager.getImage("btn_arrow_h").getFlippedCopy(true, false));
        this.btnLeft.setMouseDownImage(ResourceManager.getImage("btn_arrow_p").getFlippedCopy(true, false));
        this.btnLeft.addListener(new ComponentListener()
        {
            @Override
            public void componentActivated(AbstractComponent source)
            {
                getGameData().decrementLevel();
                updateThumbnail();
            }
        });

        updateThumbnail();
    }

    private void updateThumbnail()
    {
        if (getGameData().getLevelCount() > 0)
            this.levelThumbnail = ResourceManager.getLevelImage(getGameData().getLevels().get(getGameData().getCurrentLevel()).getSimpleName());
        else
            this.levelThumbnail = ResourceManager.getImage("no_levels");
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, float dt) throws SlickException
    {
        // Nothing to update! Woo hoo!
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException
    {
        g.setBackground(this.backgroundColor);
        g.setColor(Color.white);
        titleFont.drawString(140, 50, "World's Hardest Game");

        g.setColor(Color.black);
        g.setLineWidth(2f);
        g.drawRect(199, 178, 402, 272);

        this.levelThumbnail.draw(200, 180, .5f);

        this.btnPlay.render(gc, g);
        this.btnLeft.render(gc, g);
        this.btnRight.render(gc, g);
    }

    @Override
    public int getID()
    {
        return GameState.MAIN_MENU;
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

    @Override
    public void enter(GameContainer gc, StateBasedGame game) throws SlickException
    {
        updateThumbnail();
    }

    @Override
    public void leave(GameContainer gc, StateBasedGame game) throws SlickException
    {
        gc.getInput().clearKeyPressedRecord();
    }
}

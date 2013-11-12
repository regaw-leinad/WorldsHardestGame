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
import com.regawmod.hardestgame.GameData;
import com.regawmod.hardestgame.GameMain;
import com.regawmod.hardestgame.Resources;
import com.regawmod.slick.ui.Button;

/**
 * The main menu state.
 * 
 * @author Dan Wager
 */
public class MainMenuState extends AbstractGameState
{
    /** The main game instance */
    private GameMain gameMain;

    private Font title = new Font("Consolas", Font.BOLD, 48);
    private TrueTypeFont titleFont = new TrueTypeFont(title, true);
    private Button playButton;
    private Button btnLeft;
    private Button btnRight;
    private Image levelThumbnail;

    /**
     * Creates a new {@link MainMenuState}.
     * 
     * @param game The main game instance
     */
    public MainMenuState(GameMain game)
    {
        this.gameMain = game;
    }

    @Override
    public void init(GameContainer gc, final StateBasedGame game) throws SlickException
    {
        for (int i = 0; i < getGameData().getLevels().size(); i++)
            Resources.getLevelImage(getGameData().getLevels().get(i).getSimpleName());

        this.playButton = new Button(gc, Resources.getImage("play_normal"), 260, 490);
        this.playButton.setCenterX(400);
        this.playButton.setMouseDownColor(Color.yellow);
        this.playButton.setMouseOverColor(Color.green);
        this.playButton.addListener(new ComponentListener()
        {
            @Override
            public void componentActivated(AbstractComponent source)
            {
                game.enterState(GameState.IN_GAME);
            }
        });

        this.btnLeft = new Button(gc, Resources.getImage("btn_left"), 120, 270);
        this.btnLeft.setCenterX(100);
        this.btnLeft.setMouseDownColor(Color.yellow);
        this.btnLeft.setMouseOverColor(Color.green);
        this.btnLeft.addListener(new ComponentListener()
        {
            @Override
            public void componentActivated(AbstractComponent source)
            {
                getGameData().decrementLevel();
                updateThumbnail();
            }
        });

        this.btnRight = new Button(gc, Resources.getImage("btn_right"), 600, 270);
        this.btnRight.setCenterX(700);
        this.btnRight.setMouseDownColor(Color.yellow);
        this.btnRight.setMouseOverColor(Color.green);
        this.btnRight.addListener(new ComponentListener()
        {
            @Override
            public void componentActivated(AbstractComponent source)
            {
                getGameData().incrementLevel();
                updateThumbnail();
            }
        });

        updateThumbnail();
    }

    private void updateThumbnail()
    {
        this.levelThumbnail = Resources.getLevelImage(getGameData().getLevels().get(getGameData().getCurrentLevel()).getSimpleName());
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, float dt) throws SlickException
    {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException
    {
        g.setBackground(Color.gray);
        g.setColor(Color.white);
        titleFont.drawString(180, 50, "Open Hardest Game");

        this.levelThumbnail.draw(200, 200, .5f);

        this.playButton.render(gc, g);
        this.btnLeft.render(gc, g);
        this.btnRight.render(gc, g);
    }

    @Override
    public int getID()
    {
        return GameState.MAIN_MENU;
    }

    private GameData getGameData()
    {
        return this.gameMain.getGameData();
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame game) throws SlickException
    {
        updateThumbnail();
    }
}

package com.regawmod.hardestgame.state;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import com.regawmod.hardestgame.GameMain;

public class MainMenuState extends AbstractGameState
{
    //private TrueTypeFont gameFont;
    private GameMain gameMain;

    public MainMenuState(GameMain game)
    {
        this.gameMain = game;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException
    {
        //gameFont = new TrueTypeFont(new Font("Veranda", Font.BOLD, 40), true);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, float dt) throws SlickException
    {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException
    {
        g.setColor(Color.white);
        //gameFont.drawString(400, 300, "Testing");
        g.drawString("Deaths: " + gameMain.getAmountOfDeaths(), 400, 300);
    }

    @Override
    public int getID()
    {
        return GameState.MAIN_MENU;
    }

}

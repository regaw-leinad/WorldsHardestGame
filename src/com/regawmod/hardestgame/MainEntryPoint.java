package com.regawmod.hardestgame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class MainEntryPoint
{
    public static void main(String[] args)
    {
        try
        {
            AppGameContainer game = new AppGameContainer(new GameMain("World's Hardest Game - NSCC CSC"));
            game.setAlwaysRender(true);
            game.setDisplayMode(800, 600, false);
            game.setShowFPS(true);
            game.setTargetFrameRate(60);
            game.setVSync(true);
            game.start();
        }
        catch (SlickException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

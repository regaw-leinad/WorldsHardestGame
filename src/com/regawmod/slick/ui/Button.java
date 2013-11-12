package com.regawmod.slick.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.MouseOverArea;

public class Button extends MouseOverArea
{
    public Button(GameContainer gc, Image image, int x, int y)
    {
        super(gc, image, x, y, image.getWidth(), image.getHeight());
    }

    public float getCenterX()
    {
        return getX() + getWidth() / 2;
    }

    public void setCenterX(float cX)
    {
        setX(cX - getWidth() / 2);
    }

    public float getCenterY()
    {
        return getY() + getHeight() / 2;
    }

    public void setCenterY(float cY)
    {
        setY(cY - getHeight() / 2);
    }
}

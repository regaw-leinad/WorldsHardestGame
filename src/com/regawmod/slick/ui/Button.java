package com.regawmod.slick.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.MouseOverArea;

/**
 * A button for use in Slick engines
 * 
 * @author Dan Wager
 */
public class Button extends MouseOverArea
{
    /**
     * Creates a new {@link Button}
     * 
     * @param gc The game container
     * @param image The initial image
     * @param x The X coordinate of the top-left corner
     * @param y The Y coordinate of the top-left corner
     */
    public Button(GameContainer gc, Image image, int x, int y)
    {
        super(gc, image, x, y, image.getWidth(), image.getHeight());
    }

    /**
     * Gets a value indicating the X coordinate of the center point
     * 
     * @return The X coordinate of the center point
     */
    public float getCenterX()
    {
        return getX() + getWidth() / 2;
    }

    /**
     * Sets the center point's X coordinate
     * 
     * @param cX The X coordinate of the center point
     */
    public void setCenterX(float cX)
    {
        setX(cX - getWidth() / 2);
    }

    /**
     * Gets a value indicating the Y coordinate of the center point
     * 
     * @return The Y coordinate of the center point
     */
    public float getCenterY()
    {
        return getY() + getHeight() / 2;
    }

    /**
     * Sets the center point's Y coordinate
     * 
     * @param cy The Y coordinate of the center point
     */
    public void setCenterY(float cY)
    {
        setY(cY - getHeight() / 2);
    }
}

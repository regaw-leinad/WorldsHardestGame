package com.regawmod.entity;

import org.newdawn.slick.geom.Shape;
import com.regawmod.slick.interfaces.Renderable;
import com.regawmod.slick.interfaces.Updatable;

/**
 * A base entity with position and size.
 * 
 * @author Dan Wager
 */
public abstract class Entity implements Updatable, Renderable
{
    /** The entity's body */
    private Shape body;
    /** If the entity should be removed */
    private boolean shouldRemove;

    /**
     * Creates a new {@link Entity}.
     * 
     * @param boundingShape The bounding {@link Shape} of the entity
     */
    protected Entity(Shape boundingShape)
    {
        this.body = boundingShape;
        this.shouldRemove = false;
    }

    /**
     * Gets a value indicating the X coordinate of the entity.
     * 
     * @return The X coordinate of the entity
     */
    public float getX()
    {
        return this.body.getX();
    }

    /**
     * Gets a value indicating the center X coordinate of the entity.
     * 
     * @return The center X coordinate of the entity
     */
    public float getCenterX()
    {
        return this.body.getCenterX();
    }

    /**
     * Sets the entity's X coordinate.
     * 
     * @param x The X coordinate
     */
    public void setX(float x)
    {
        this.body.setX(x);
    }

    /**
     * Sets the entity's center X coordinate.
     * 
     * @param cX The center X coordinate
     */
    public void setCenterX(float cX)
    {
        this.body.setCenterX(cX);
    }

    /**
     * Gets a value indicating the Y coordinate of the entity
     * 
     * @return The Y coordinate of the entity
     */
    public float getY()
    {
        return this.body.getY();
    }

    /**
     * Gets a value indicating the center Y coordinate of the entity
     * 
     * @return The center Y coordinate of the entity
     */
    public float getCenterY()
    {
        return this.body.getCenterY();
    }

    /**
     * Sets the entity's Y coordinate
     * 
     * @param y The Y coordinate
     */
    public void setY(float y)
    {
        this.body.setY(y);
    }

    /**
     * Sets the entity's center Y coordinate
     * 
     * @param cY The center Y coordinate
     */
    public void setCenterY(float cY)
    {
        this.body.setCenterY(cY);
    }

    /**
     * Gets a value indicating the width of the entity
     * 
     * @return The width of the entity
     */
    public float getWidth()
    {
        return this.body.getWidth();
    }

    /**
     * Gets a value indicating the height of the entity
     * 
     * @return The height of the entity
     */
    public float getHeight()
    {
        return this.body.getHeight();
    }

    /**
     * Gets a value indicating if the entity should be removed
     * 
     * @return If the entity should be removed
     */
    public boolean shouldRemove()
    {
        return this.shouldRemove;
    }

    /**
     * Flags the entity for removal
     */
    public void flagForRemoval()
    {
        this.shouldRemove = true;
    }

    /**
     * Why am I exposing this???? Need to get rid of this. Find another way for wall collision detection! Damnit!
     * 
     * @return The entity's bounding shape
     */
    public Shape getBody()
    {
        return this.body;
    }
}

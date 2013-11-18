package com.regawmod.entity;

import org.newdawn.slick.geom.Shape;

/**
 * An entity that has the ability to move
 * 
 * @author Dan Wager
 */
public abstract class MovableEntity extends Entity
{
    /** The entity's speed */
    private float speed;

    /**
     * Creates a new {@link MovableEntity}
     * 
     * @param boundingShape The bounding {@link Shape} of the entity
     * @param speed The entity's initial speed
     */
    protected MovableEntity(Shape boundingShape, float speed)
    {
        super(boundingShape);

        this.speed = speed;
    }

    /**
     * Moves the entity in the X direction by the specified amount.
     * 
     * @param dx How many pixels to move in the X direction
     */
    public void moveX(float dx)
    {
        this.setX(this.getX() + dx);
    }

    /**
     * Moves the entity in the Y direction by the specified amount.
     * 
     * @param dy How many pixels to move in the Y direction
     */
    public void moveY(float dy)
    {
        this.setY(this.getY() + dy);
    }

    /**
     * Sets the speed of the entity.
     * 
     * @param speed The speed in pixels
     */
    public void setSpeed(float speed)
    {
        this.speed = speed;
    }

    /**
     * Gets a value indicating the speed of the entity.
     * 
     * @return The speed of the entity
     */
    public float getSpeed()
    {
        return this.speed;
    }

    /**
     * Gets a value indicating the amount of pixels to move when backtracking to the wall after collision.
     * 
     * @param dt The delta time between frames
     * @return The distance to travel backwards
     */
    protected float getBactrackComponent(float dt)
    {
        return getBactrackComponent(this.speed, dt);
    }

    /**
     * Gets a value indicating the amount of pixels to move when backtracking to the wall after collision.
     * 
     * @param speed The current speed of the entity
     * @param dt The delta time between frames
     * @return The distance to travel backwards
     */
    protected float getBactrackComponent(float speed, float dt)
    {
        return speed / Math.abs(speed) * -dt;
    }

    /**
     * Turns the entity around by multiplying speed by -1.
     */
    protected void turnAround()
    {
        this.speed *= -1;
    }
}

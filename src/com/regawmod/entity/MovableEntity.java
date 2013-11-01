package com.regawmod.entity;

import org.newdawn.slick.geom.Shape;

public abstract class MovableEntity extends Entity
{
    protected float speed;

    public MovableEntity(Shape boundingShape, float speed)
    {
        super(boundingShape);

        this.speed = speed;
    }

    public void moveX(float dx)
    {
        this.setX(this.getX() + dx);
    }

    public void moveY(float dy)
    {
        this.setY(this.getY() + dy);
    }
}

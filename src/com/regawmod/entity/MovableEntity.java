package com.regawmod.entity;

import org.newdawn.slick.GameContainer;
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

    public void setSpeed(float speed)
    {
        this.speed = speed;
    }

    public float getSpeed()
    {
        return this.speed;
    }

    @Override
    public void update(GameContainer gc, float dt)
    {
    }

    //
    //    @Override
    //    public final void update(GameContainer gc, float dt)
    //    {
    //        update(gc.getInput(), dt);
    //    }
    //
    //    protected abstract void update(Input input, float dt);

    protected float getBactrackComponent(float dt)
    {
        return this.speed / Math.abs(this.speed) * -dt;
    }

    protected void turnAround()
    {
        this.speed *= -1;
    }
}

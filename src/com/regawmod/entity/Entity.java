package com.regawmod.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import com.regawmod.slick.interfaces.Renderable;
import com.regawmod.slick.interfaces.Updatable;

public abstract class Entity implements Updatable, Renderable
{
    private Shape body;
    private boolean shouldRemove;

    public Entity(Shape boundingShape)
    {
        this.body = boundingShape;
        this.shouldRemove = false;
    }

    @Override
    public abstract void update(GameContainer gc, float dt);

    @Override
    public abstract void render(Graphics g);

    public float getX()
    {
        return this.body.getX();
    }

    public float getCenterX()
    {
        return this.body.getCenterX();
    }

    public void setX(float x)
    {
        this.body.setX(x);
    }

    public void setCenterX(float cX)
    {
        this.body.setCenterX(cX);
    }

    public float getY()
    {
        return this.body.getY();
    }

    public float getCenterY()
    {
        return this.body.getCenterY();
    }

    public void setY(float y)
    {
        this.body.setY(y);
    }

    public void setCenterY(float cY)
    {
        this.body.setCenterY(cY);
    }

    public float getWidth()
    {
        return this.body.getWidth();
    }

    public float getHeight()
    {
        return this.body.getHeight();
    }

    public boolean shouldRemove()
    {
        return this.shouldRemove;
    }

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

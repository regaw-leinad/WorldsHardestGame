package com.regawmod.hardestgame.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import com.regawmod.entity.MovableEntity;
import com.regawmod.hardestgame.level.Level;

public final class Player extends MovableEntity
{
    public static final float SIZE = 26f;
    public static final float SPEED = 100f;

    private Level level;

    public Player(Level level)
    {
        super(new Rectangle(level.getPlayerStartX(), level.getPlayerStartY(), SIZE, SIZE), 100f);

        this.level = level;
    }

    @Override
    public void update(GameContainer gc, float dt)
    {
        Input input = gc.getInput();

        float distance = this.speed * dt;

        updateY(input, distance);
        updateX(input, distance);
    }

    private void updateX(Input input, float distance)
    {
        float dx = 0;

        if (input.isKeyDown(Input.KEY_A))
            dx -= distance;

        if (input.isKeyDown(Input.KEY_D))
            dx += distance;

        moveAndCheckCollisionsX(dx);
    }

    private void updateY(Input in, float distance)
    {
        float dy = 0;

        if (in.isKeyDown(Input.KEY_W))
            dy -= distance;

        if (in.isKeyDown(Input.KEY_S))
            dy += distance;

        moveAndCheckCollisionsY(dy);
    }

    private void moveAndCheckCollisionsY(float dy)
    {
        this.moveY(dy);

        while (this.collidedWithWall())
        {
            if (dy <= 0)
                this.moveY(.1f);
            else
                this.moveY(-.1f);
        }
    }

    private void moveAndCheckCollisionsX(float dx)
    {
        this.moveX(dx);

        while (this.collidedWithWall())
        {
            if (dx <= 0)
                this.moveX(.1f);
            else
                this.moveX(-.1f);
        }
    }

    @Override
    public void render(Graphics g)
    {
        drawPlayer(g);
    }

    private void drawPlayer(Graphics g)
    {
        g.setColor(Color.black);
        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        // g.drawRect(this.getX() - 4, this.getY() - 4, this.getWidth() + 7, this.getHeight() + 7);
        g.setColor(Color.red);
        g.fillRect(this.getX() + 4, this.getY() + 4, this.getWidth() - 8, this.getHeight() - 8);
    }

    private boolean collidedWithWall()
    {
        return this.level.collidesWithWall(this);
    }
}

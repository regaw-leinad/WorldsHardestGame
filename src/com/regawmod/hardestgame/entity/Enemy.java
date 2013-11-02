package com.regawmod.hardestgame.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import com.regawmod.entity.MovableEntity;
import com.regawmod.hardestgame.level.Level;

public class Enemy extends MovableEntity
{
    public static final float ENEMY_RADIUS = 8;

    protected Level level;

    public Enemy(float x, float y, Level level)
    {
        super(new Circle(x, y, ENEMY_RADIUS), 0f);

        this.level = level;
    }

    @Override
    public final void render(Graphics g)
    {
        g.setColor(Color.black);
        g.fillOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        g.setColor(Color.blue);
        g.fillOval(this.getX() + 3, this.getY() + 3, this.getWidth() - 6, this.getHeight() - 6);
    }

    @Override
    public void update(GameContainer gc, float dt)
    {

    }
}

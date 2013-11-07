package com.regawmod.hardestgame.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import com.regawmod.entity.MovableEntity;
import com.regawmod.hardestgame.level.Level;

public abstract class Enemy extends MovableEntity
{
    public static final float ENEMY_RADIUS = 8;

    private boolean boundedByLevel;
    private Level level;

    public Enemy(float x, float y, Level level)
    {
        this(x, y, level, false);
    }

    public Enemy(float x, float y, Level level, boolean bounded)
    {
        super(new Circle(x, y, ENEMY_RADIUS), 0f);

        this.level = level;
        setBoundedByLevel(bounded);
    }

    @Override
    public final void update(GameContainer gc, float dt)
    {
        update(dt);
    }

    protected abstract void update(float dt);

    @Override
    public final void render(Graphics g)
    {
        //drawBoundingShape(g);

        drawBlueEnemy(g);
        //        drawGreenFaceEnemy(g);
    }

    private void drawBoundingShape(Graphics g)
    {
        g.setColor(Color.cyan);
        g.draw(this.getBody());
    }

    private void drawGreenFaceEnemy(Graphics g)
    {
        g.setColor(Color.black);
        g.fillOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        g.setColor(Color.green);
        g.fillOval(this.getX() + 1, this.getY() + 1, this.getWidth() - 2, this.getHeight() - 2);

        g.setColor(Color.black);
        g.fillOval(this.getCenterX() - this.getWidth() / 4 - 1, this.getCenterY() - this.getWidth() / 4 + 1, 2, 2);
        g.fillOval(this.getCenterX() + this.getWidth() / 4 - 1, this.getCenterY() - this.getWidth() / 4 + 1, 2, 2);

        g.drawLine(this.getCenterX() - this.getWidth() / 4, this.getCenterY() + this.getWidth() / 4 - 1,
                this.getCenterX() + this.getWidth() / 4 - 1, this.getCenterY() + this.getWidth() / 4 - 1);
    }

    private void drawBlueEnemy(Graphics g)
    {
        g.setColor(Color.black);
        g.fillOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        g.setColor(Color.blue);
        g.fillOval(this.getX() + 3, this.getY() + 3, this.getWidth() - 6, this.getHeight() - 6);
    }

    public boolean isBoundedByLevel()
    {
        return this.boundedByLevel;
    }

    public void setBoundedByLevel(boolean bounded)
    {
        this.boundedByLevel = bounded;
    }

    protected boolean collidesWithWall()
    {
        return this.level.collidesWithWall(this);
    }

    protected boolean collidesWithZone()
    {
        return this.level.collidesWithZones(this);
    }

    public void onCoinCollected(float coinX, float coinY, int coinsRemaining)
    {
    }

    public void onPlayerDeath()
    {
    }

    public void onPlayerRespawn()
    {
    }
}

package com.regawmod.hardestgame.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import com.regawmod.entity.MovableEntity;
import com.regawmod.hardestgame.level.Level;

/**
 * The base Enemy
 * 
 * @author Dan Wager
 */
public abstract class Enemy extends MovableEntity
{
    /** The radius of the enemy */
    public static final float ENEMY_RADIUS = 8;

    /** The level */
    private Level level;
    /** If the enemy should be bounded by the level's bounds */
    private boolean boundedByLevel;

    /**
     * Creates a new {@link Enemy}
     * 
     * @param x The enemy's X position
     * @param y The enemy's Y position
     * @param level The level
     */
    protected Enemy(float x, float y, Level level)
    {
        this(x, y, level, false);
    }

    /**
     * Creates a new {@link Enemy}
     * 
     * @param x The enemy's X position
     * @param y The enemy's Y position
     * @param level The level
     * @param bounded If the enemy is bounded by the level
     */
    protected Enemy(float x, float y, Level level, boolean bounded)
    {
        super(new Circle(x, y, ENEMY_RADIUS), 0f);

        this.level = level;
        setBoundedByLevel(bounded);
    }

    /**
     * Gets a value indicating if the enemy collided with a wall this frame.
     * 
     * @return If the enemy collided with a wall
     */
    protected boolean collidesWithWall()
    {
        return this.level.collidesWithWall(this);
    }

    /**
     * Gets a value indicating if the enemy collided with the start or end zone.
     * 
     * @return If the enemy collided with a zone
     */
    protected boolean collidesWithZone()
    {
        return this.level.collidesWithZones(this);
    }

    /**
     * Gets a value indicating if the enemy is bounded by the level.
     * 
     * @return If the enemy is bounded
     */
    public boolean isBoundedByLevel()
    {
        return this.boundedByLevel;
    }

    /**
     * Delegate method that is run when a coin is collected by the player.
     * 
     * @param coinX The X coordinate of the coin
     * @param coinY The Y coordinate of the coin
     * @param coinsRemaining How many coins are remaining
     */
    public void onCoinCollected(float coinX, float coinY, int coinsRemaining)
    {
    }

    /**
     * Delegate method that is run when the player dies.
     */
    public void onPlayerDeath()
    {
    }

    /**
     * Delegate method that is run then the player respawns after a death.
     */
    public void onPlayerRespawn()
    {
    }

    @Override
    public final void render(Graphics g)
    {
        renderBlueEnemy(g);
    }

    /**
     * Renders the blue version of the enemy.
     * 
     * @param g The graphics object
     */
    private void renderBlueEnemy(Graphics g)
    {
        g.setColor(Color.black);
        g.fillOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        g.setColor(Color.blue);
        g.fillOval(this.getX() + 3, this.getY() + 3, this.getWidth() - 6, this.getHeight() - 6);
    }

    /**
     * Renders the enemy's bounding shape.
     * 
     * @param g The graphics object
     */
    private void renderBoundingShape(Graphics g)
    {
        g.setColor(Color.cyan);
        g.draw(this.getBody());
    }

    /**
     * Renders the green version of the enemy.
     * 
     * @param g The graphics object
     */
    private void renderGreenFaceEnemy(Graphics g)
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

    /**
     * Sets if the enemy is bounded by the level.
     * 
     * @param bounded If the enemy is bounded by the level
     */
    public void setBoundedByLevel(boolean bounded)
    {
        this.boundedByLevel = bounded;
    }

    /**
     * Update the enemy's logic based on the amount of time that has passed.
     * 
     * @param dt The amount of time that has passed in seconds since the last update
     */
    protected void update(float dt)
    {
    }

    @Override
    public final void update(GameContainer gc, float dt)
    {
        update(dt);
    }
}

package com.regawmod.hardestgame.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import com.regawmod.entity.MovableEntity;
import com.regawmod.hardestgame.entity.enemy.render.BlueEnemyRenderer;
import com.regawmod.hardestgame.entity.enemy.render.EnemyRenderer;
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

    private EnemyRenderer enemyRenderer;

    /**
     * Creates a new {@link Enemy}
     * 
     * @param x The enemy's X position
     * @param y The enemy's Y position
     * @param level The level
     */
    public Enemy(float x, float y, Level level)
    {
        super(new Circle(x, y, ENEMY_RADIUS), 0f);

        this.level = level;
        this.setEnemyRenderer(new BlueEnemyRenderer(this));
    }

    /**
     * Gets a value indicating if the enemy collided with a wall this frame.
     * 
     * @return If the enemy collided with a wall
     */
    protected final boolean collidesWithWall()
    {
        return this.level.collidesWithWall(this);
    }

    /**
     * Gets a value indicating if the enemy collided with the start or end zone.
     * 
     * @return If the enemy collided with a zone
     */
    protected final boolean collidesWithZone()
    {
        return this.level.collidesWithZones(this);
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
     * Delegate method that is called when the player dies.
     */
    public void onPlayerDeath()
    {
    }

    /**
     * Delegate method that is called when the player respawns after a death.
     */
    public void onPlayerRespawn()
    {
    }

    /**
     * Sets the enemy's render behavior
     * 
     * @param enemyRenderer The renderer for the enemy
     */
    public void setEnemyRenderer(EnemyRenderer enemyRenderer)
    {
        this.enemyRenderer = enemyRenderer;
    }

    /**
     * Gets the enemy's renderer
     * 
     * @return The enemy's renderer
     */
    public EnemyRenderer getEnemyRenderer()
    {
        return this.enemyRenderer;
    }

    @Override
    public final void render(Graphics g)
    {
        this.enemyRenderer.renderEnemy(g);
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

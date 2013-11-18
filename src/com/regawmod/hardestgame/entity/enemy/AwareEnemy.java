package com.regawmod.hardestgame.entity.enemy;

import com.regawmod.hardestgame.entity.Enemy;
import com.regawmod.hardestgame.level.Level;

/**
 * An enemy that is aware of the player and follows the player slowly.
 * 
 * @author Dan Wager
 */
public class AwareEnemy extends Enemy
{
    /** The current level */
    private Level level;
    /** The current angle of travel */
    private double theta;
    /** If we should track the player or not */
    private boolean shouldTrack;

    /**
     * Creates a new {@link AwareEnemy}.
     * 
     * @param x The enemy's X position
     * @param y Then enemy's Y position
     * @param level The level
     */
    public AwareEnemy(float x, float y, Level level)
    {
        super(x, y, level);

        this.level = level;
        this.shouldTrack = true;

        setSpeed(40f);
    }

    @Override
    protected void update(float dt)
    {
        if (this.shouldTrack)
        {
            calculateTheta(this.level.getPlayerX(), this.level.getPlayerY());
            moveEnemy(dt);
        }
    }

    /**
     * Moves the enemy towards the player
     * 
     * @param dt Delta time
     */
    private void moveEnemy(float dt)
    {
        float xSpeed = (float)(Math.cos(theta) * this.getSpeed() * dt);
        float ySpeed = (float)(Math.sin(theta) * this.getSpeed() * dt);

        this.moveX(xSpeed);

        while (this.collidesWithWall() || this.collidesWithZone())
            this.moveX(this.getBactrackComponent(xSpeed, dt));

        this.moveY(ySpeed);

        while (this.collidesWithWall() || this.collidesWithZone())
            this.moveY(this.getBactrackComponent(ySpeed, dt));
    }

    /**
     * Calculates theta based on positions
     * 
     * @param toX X coordinate of end point
     * @param toY Y coordinate of end point
     */
    private void calculateTheta(float toX, float toY)
    {
        this.theta = Math.atan2(toY - this.getCenterY(), toX - this.getCenterX());
    }

    @Override
    public void onCoinCollected(float coinX, float coinY, int coinsRemaining)
    {
    }

    @Override
    public void onPlayerDeath()
    {
        this.shouldTrack = false;
    }

    @Override
    public void onPlayerRespawn()
    {
        this.shouldTrack = true;
    }
}

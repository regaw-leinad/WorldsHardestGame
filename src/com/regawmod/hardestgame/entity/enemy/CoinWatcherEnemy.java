package com.regawmod.hardestgame.entity.enemy;

import com.regawmod.hardestgame.entity.Enemy;
import com.regawmod.hardestgame.level.Level;

/**
 * An enemy that responds to the player collecting coins.
 * 
 * @author Dan Wager
 */
public class CoinWatcherEnemy extends Enemy
{
    /** If the enemy has made it to it's desired location */
    private boolean inPosition;
    /** The distance from the enemy to the target location */
    private double totalDistance;
    /** The angle of travel */
    private double theta;
    /** The start X coordinate */
    private float startX;
    /** The start Y coordinate */
    private float startY;

    /**
     * Creates a new {@link CoinWatcherEnemy}.
     * 
     * @param x The enemy's X position
     * @param y The enemy's Y position
     * @param level The level
     */
    public CoinWatcherEnemy(float x, float y, Level level)
    {
        super(x, y, level);

        this.inPosition = true;
        setSpeed(100f);

        this.startX = x;
        this.startY = y;
    }

    @Override
    protected void update(float dt)
    {
        if (!inPosition)
        {
            setSpeed(100);

            if (this.totalDistance <= 0)
            {
                inPosition = true;
                setSpeed(100f);
            }
            else
            {
                moveEnemy(dt);
            }
        }
        else
        {
            this.moveY(getSpeed() * dt);

            if (this.collidesWithWall() || this.collidesWithZone())
            {
                while (this.collidesWithWall() || this.collidesWithZone())
                    this.moveY(this.getBactrackComponent(dt));

                this.turnAround();
            }
        }
    }

    /**
     * Moves the enemy based on delta time
     * 
     * @param dt Delta time
     */
    private void moveEnemy(float dt)
    {
        this.moveX((float)(Math.cos(theta) * getSpeed() * dt));
        this.moveY((float)(Math.sin(theta) * getSpeed() * dt));

        totalDistance -= getSpeed() * dt;
    }

    @Override
    public void onCoinCollected(float coinX, float coinY, int coinsRemaining)
    {
        this.inPosition = false;

        this.theta = Math.atan2(coinY - this.getCenterY(), coinX - this.getCenterX());
        totalDistance = Math.hypot(coinX - this.getCenterX(), coinY - this.getCenterY());
    }

    @Override
    public void onPlayerDeath()
    {
        onCoinCollected(this.startX, this.startY, 0);
    }
}
